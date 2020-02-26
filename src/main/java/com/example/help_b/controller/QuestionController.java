package com.example.help_b.controller;

import com.example.help_b.component.Enum.GeneralEnum;
import com.example.help_b.model.Comment;
import com.example.help_b.model.QuestionDto;
import com.example.help_b.service.CommentService;
import com.example.help_b.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class QuestionController {
    @Resource(name = "questionServiceImpl")
    QuestionService questionService;
    @Resource(name = "commentServiceImpl")
    CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id")Integer questionId,
                           Model model){
        QuestionDto questionDto = questionService.selectQuestionById(questionId);
        questionService.updateReadCount(questionId);
        List<Comment> comments = commentService.selectCommentsById(questionId, GeneralEnum.QUESTION.getCode());
        model.addAttribute("question",questionDto);
        model.addAttribute("comments",comments);
        return "question";
    }
}
