package com.example.help_b.controller;

import com.example.help_b.model.BasicUser;
import com.example.help_b.model.Question;
import com.example.help_b.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PublishQuestionController {
    @Resource(name = "simpleDateFormat")
    SimpleDateFormat simpleDateFormat;
    @Resource(name = "questionServiceImpl")
    QuestionService questionService;
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String publish(Question question,
                        HttpServletRequest request,
                        Model model){
        BasicUser basicUser= (BasicUser) request.getSession().getAttribute("user");
        question.setAuthor(basicUser.getId());
        question.setCreatedAt(simpleDateFormat.format(new Date()));
        question.setModifiedAt(question.getCreatedAt());
        System.out.println(question);
        questionService.insertQuestion(question);
        return "redirect:/";
    }
}
