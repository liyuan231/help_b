package com.example.help_b.service;

import com.example.help_b.model.GitHubUser;
import com.example.help_b.model.SysUser;

public interface UserService {
    public void insertOrUpdateSysUser(SysUser sysUser);
    public void insertOrUpdateGitHubUser(GitHubUser GithubUser);
    SysUser selectSysUserByUsername(String username);
    GitHubUser selectGitHubUserByUsername(String username);
    SysUser selectSysUserById(Integer id);
    GitHubUser selectGitHubUserById(Integer id);
}
