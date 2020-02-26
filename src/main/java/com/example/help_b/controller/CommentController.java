package com.example.help_b.controller;

import com.example.help_b.component.Enum.GeneralEnum;
import com.example.help_b.component.exception.MyException;
import com.example.help_b.model.Comment;
import com.example.help_b.model.ResultJson;
import com.example.help_b.service.CommentService;
import com.example.help_b.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class CommentController {
    @Resource(name = "commentServiceImpl")
    CommentService commentService;
    @Resource(name = "userServiceImpl")
    UserService userService;
    @Resource(name = "simpleDateFormat")
    SimpleDateFormat simpleDateFormat;
    @PostMapping("/comment")
    public ResultJson comment(@RequestBody Comment comment){
        if(comment.getContent().equals("")||comment.getContent()==null){
            throw  new MyException(GeneralEnum.COMMENT_EMPTY);
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        comment.setBasicUser(userService.selectSysUserByUsername(username));
        comment.setCreatedAt(simpleDateFormat.format(new Date()));
        comment.setModifiedAt(comment.getCreatedAt());
        comment.setPraiseCount(0);
        commentService.insertComment(comment);
        return new ResultJson(200,"success",null);
    }
    @GetMapping("/comment/{id}")
    public ResultJson commentsToComment(@PathVariable("id")Integer id){
        List<Comment> comments=commentService.selectCommentsById(id,GeneralEnum.COMMENT.getCode());

        return new ResultJson(200,"success",comments);
    }
}
