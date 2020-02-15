package com.example.help_b.controller;

import com.auth0.jwt.JWT;
import com.example.help_b.model.BasicUser;
import com.example.help_b.model.Page;
import com.example.help_b.model.QuestionDto;
import com.example.help_b.service.PageService;
import com.example.help_b.service.QuestionService;
import com.example.help_b.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Value("${page.questions.size}")
    private  Integer size;
    @Resource(name = "pageServiceImpl")
    PageService pageService;

    @Resource(name = "questionServiceImpl")
    QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model){
        List<QuestionDto> questions = questionService.getQuestions(1,size);
        model.addAttribute("questions",questions);
        Page pageInfo= pageService.wrestleWithPage(1,questionService.sum(),size);
        model.addAttribute("page",pageInfo);
        return "index";
    }
    @GetMapping("/page/{page}")
    public String _index(@PathVariable("page")Integer page,
                         Model model){
//        Integer sum = questionService.sum();
        List<QuestionDto> questionDtos = questionService.getQuestions(page,size);
        model.addAttribute("questions",questionDtos);
        Page pageInfo = pageService.wrestleWithPage(page,questionService.sum(),size);
        model.addAttribute("page",pageInfo);
        return "index";
    }

}
