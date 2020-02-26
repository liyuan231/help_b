package com.example.help_b.service;

import com.example.help_b.model.Comment;

import java.util.List;

public interface CommentService {
    void insertComment(Comment comment);

    List<Comment> selectCommentsById(Integer id,Integer type);


}
