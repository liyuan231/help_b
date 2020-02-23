package com.example.help_b.service.impl;

import com.example.help_b.dao.UserDao;
import com.example.help_b.model.BasicUser;
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
    public void insertOrUpdateSysUser(SysUser sysUser) {
        List<SysUser> users=userDao.selectSysUserById(sysUser.getId());
        if(users.size()==0){
            userDao.insertSysUser(sysUser);
        }else{
            userDao.updateSysUser(sysUser);
        }

    }

    @Override
    public void insertOrUpdateGitHubUser(GitHubUser githubUser) {
        List<GitHubUser> users=userDao.selectGitHubUserById(githubUser.getId());
        if(users.size()==0){
            userDao.insertGitHubUser(githubUser);
        }else{
            userDao.updateGitHubUser(githubUser);
        }
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
