package com.example.help_b.service.impl;

import com.example.help_b.component.Enum.GeneralEnum;
import com.example.help_b.component.exception.MyException;
import com.example.help_b.dao.CommentDao;
import com.example.help_b.dao.QuestionDao;
import com.example.help_b.model.Comment;
import com.example.help_b.model.Question;
import com.example.help_b.service.CommentService;
import com.example.help_b.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource(name = "commentDao")
    CommentDao commentDao;
    @Resource(name = "questionDao")
    QuestionDao questionDao;
    @Resource(name = "userServiceImpl")
    UserService userService;
    @Override
    public void insertComment(Comment comment) {
        Question question=null;
        if(comment.getParentId()==0||comment.getParentId()==null){
            throw new MyException(GeneralEnum.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType()==null){
            throw new MyException(GeneralEnum.TYPE_PARAM_WRONG);
        }
        //用于判断回复评论或是问题
        if(comment.getType() == GeneralEnum.COMMENT.getCode()){
            //回复评论
            Comment dbComment=commentDao.selectCommentById(comment.getParentId());
            if(dbComment ==null){
                throw new MyException(GeneralEnum.COMMENT_NOT_FOUND);
            }
        }else {
            //回复问题
            try {
                question = questionDao.selectQuestionById(comment.getParentId());
                question.setCommentCount(question.getCommentCount()+1);
                questionDao.updateQuestion(question);
            }catch (Exception ex){
                throw new MyException(GeneralEnum.QUESTION_NOT_FOUND);
            }

            if (question==null){
                throw new MyException(GeneralEnum.QUESTION_NOT_FOUND);
            }
        }

        commentDao.insertComment(comment);
    }

    @Override
    public List<Comment> selectCommentsByQuestionId(Integer questionId) {
        List<Comment> comments = questionDao.selectCommentsByQuestionId(questionId);
        for(Comment comment:comments){
            comment.setBasicUser(userService.selectSysUserById(comment.getBasicUser().getId()));
        }
        return comments;
    }
}

