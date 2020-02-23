package com.example.help_b.service.impl;

import com.example.help_b.component.Utils;
import com.example.help_b.dao.UserDao;
import com.example.help_b.model.Role;
import com.example.help_b.model.SysUser;
import com.example.help_b.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    @Resource(name = "userDao")
    UserDao userDao;
    @Resource(name = "bCryptPasswordEncoder")
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Resource(name ="simpleDateFormat" )
    SimpleDateFormat simpleDateFormat;
    @Resource(name = "utils")
    Utils utils;

    @Override
    public SysUser register(SysUser sysUser) throws Exception {
        List<SysUser> sysUsers=userDao.selectSysUserByUsername(sysUser.getUsername());
        if(sysUsers.size()!=0){
            //TODO
            throw new Exception();
//            throw new AuthException("用户名重复！");
        }
        String rawPassword = sysUser.getPassword();
        sysUser.setPassword( bCryptPasswordEncoder.encode(rawPassword));
        sysUser.setLastPasswordRestDate(simpleDateFormat.format(new Date()));
        userDao.insertSysUser(sysUser);
        Long roleId = sysUser.getRole().getId();
        Role role=userDao.selectRoleById(roleId);
        sysUser.setId(userDao.selectMaxId());
        sysUser.setRole(role);
        userDao.insertUserToRoleReference(sysUser.getId(),roleId);
        return sysUser;
    }

    @Override
    public String refreshToken(String oldToken) {
        SysUser sysUser = utils.getUserFromToken(oldToken);
        String newToken = utils.refreshToken(oldToken);
        return newToken;
    }

}
