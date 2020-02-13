package com.example.help_b.service.impl;

import com.example.help_b.dao.UserDao;
import com.example.help_b.model.GitHubUser;
import com.example.help_b.model.SysUser;
import com.example.help_b.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Resource(name = "userDao")
    UserDao userDao;

    @Override
    public void insertSysUser(SysUser sysUser) {
        userDao.insertSysUser(sysUser);
    }

    @Override
    public void insertGitHubUser(GitHubUser githubUser) {

        userDao.insertGitHubUser(githubUser);
    }

    @Override
    public SysUser selectSysUserByUsername(String username) {
        List<SysUser> sysUsers = userDao.selectSysUserByUsername(username);
        if(sysUsers.size()==0){
            return null;
        }
        return sysUsers.get(0);
    }

    @Override
    public GitHubUser selectGitHubUserByUsername(String username) {
        List<GitHubUser> gitHubUsers=userDao.selectGitHubUserByUsername(username);
        if(gitHubUsers.size()==0){
            return null;
        }
        return gitHubUsers.get(0);
    }

    @Override
    public SysUser selectSysUserById(Integer id) {
        List<SysUser> sysUsers = userDao.selectSysUserById(id);
        if(sysUsers.size()==0){
            return null;
        }
        return sysUsers.get(0);
    }

    @Override
    public GitHubUser selectGitHubUserById(Integer id) {
        List<GitHubUser> gitHubUsers=userDao.selectGitHubUserById(id);
        if(gitHubUsers.size()==0){
            return null;
        }
        return gitHubUsers.get(0);
    }


}
