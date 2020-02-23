package com.example.help_b.dao;

import com.example.help_b.model.GitHubUser;
import com.example.help_b.model.Role;
import com.example.help_b.model.SysUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {

    @Insert("insert into SysUser(username,avatar_url,bio,created_at,updated_at,password)values(#{username},#{avatar_url},#{bio},#{created_at},#{updated_at},#{password})")
    void insertSysUser(SysUser sysUser);
    @Insert("insert into githubuser(id,username,avatar_url,bio,created_at,updated_at)values(#{id},#{username},#{avatar_url},#{bio},#{created_at},#{updated_at})")
    void insertGitHubUser(GitHubUser GithubUser);

    @Select("select * from sysuser where username = #{username}")
    List<SysUser> selectSysUserByUsername(String username);
    @Select("select * from githubuser where username = #{username}")
    List<GitHubUser> selectGitHubUserByUsername(String username);

    @Select("select * from sysuser where id = #{id}")
    List<SysUser> selectSysUserById(Integer id);
    @Select("select * from githubuser where id = #{id}")
    List<GitHubUser> selectGitHubUserById(Integer id);

    @Update("update sysuser set username = #{username},avatar_url=#{avatar_url},bio = #{bio},created_at = #{created_at},updated_at = #{updated_at},password = #{password} where id = #{id}")
    void updateSysUser(SysUser sysUser);

    @Update("update githubuser set username = #{username},avatar_url=#{avatar_url},bio = #{bio},created_at = #{created_at},updated_at = #{updated_at} where id = #{id}")
    void updateGitHubUser(GitHubUser githubUser);

    @Select("select * from role where id = #{roleId}")
    Role selectRoleById(Long roleId);

    @Insert("insert into userToRole(userId,roleId)values(#{userId},#{roleId})")
    void insertUserToRoleReference(@Param("userId") Integer userId,
                                   @Param("roleId") Long roleId);

    @Select("select * from role where id in (select roleId from userToRole where userId = #{userId})")
    Role selectRoleByUserId(@Param("userId") Integer id);

    @Select("SELECT LAST_INSERT_ID();")
    Integer selectMaxId();

    @Select("select * from role where name = #{scope}")
    Role selectRoleByName(String scope);
}
