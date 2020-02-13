package com.example.help_b.dao;

import com.example.help_b.model.GitHubUser;
import com.example.help_b.model.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {

    @Insert("insert into SysUser(username,avatar_url,bio,created_at,updated_at,password)values(#{username},#{avatar_url},#{bio},#{created_at},#{updated_at},#{password})")
    public void insertSysUser(SysUser sysUser);
    @Insert("insert into githubuser(id,username,avatar_url,bio,created_at,updated_at)values(#{id},#{username},#{avatar_url},#{bio},#{created_at},#{updated_at})")
    public void insertGitHubUser(GitHubUser GithubUser);

    @Select("select * from sysuser where username = #{username}")
    List<SysUser> selectSysUserByUsername(String username);
    @Select("select * from githubuser where username = #{username}")
    List<GitHubUser> selectGitHubUserByUsername(String username);

    @Select("select * from sysuser where id = #{id}")
    List<SysUser> selectSysUserById(Integer id);
    @Select("select * from githubuser where id = #{id}")
    List<GitHubUser> selectGitHubUserById(Integer id);
}
