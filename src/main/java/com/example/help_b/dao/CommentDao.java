package com.example.help_b.dao;

import com.example.help_b.model.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CommentDao {
    @Insert("insert into comment(parentId,type,commentator,createdAt,modifiedAt,praiseCount,content) values(#{parentId},#{type},#{basicUser.id},#{createdAt},#{modifiedAt},#{praiseCount},#{content})")
    void insertComment(Comment comment);

    @Select("select * from comment where id = #{id}")
    Comment selectCommentById(@Param("id") Integer commentId);

    @Update("update comment set parentId = #{parentId},type = #{type},commentator = #{commentator},createdAt = #{createdAt},modifiedAt=#{modifiedAt},praiseCount = #{praiseCount},content = #{content}")
    void updateComment(Comment comment);
}
