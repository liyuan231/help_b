package com.example.help_b.controller;

import com.example.help_b.model.QuestionDto;
import com.example.help_b.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

@Controller
public class QuestionController {
    @Resource(name = "questionServiceImpl")
    QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id")Integer questionId,
                           Model model){
        QuestionDto questionDto = questionService.selectQuestionById(questionId);
        questionService.updateReadCount(questionId);

        model.addAttribute("question",questionDto);
        return "question";
    }
}
