package com.example.help_b.service.impl;

import com.example.help_b.dao.UserDao;
import com.example.help_b.model.Role;
import com.example.help_b.model.SysUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource(name = "userDao")
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
        List<SysUser>sysUsers=userDao.selectSysUserByUsername(username);
        if(sysUsers.size()==0){
            return null;
        }else {
            SysUser sysUser = sysUsers.get(0);
            Role role = userDao.selectRoleByUserId(sysUser.getId());
            sysUser.setRole(role);
            return sysUser;
        }
    }
}
