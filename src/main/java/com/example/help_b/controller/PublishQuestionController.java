package com.example.help_b.controller;

import com.example.help_b.model.BasicUser;
import com.example.help_b.model.Question;
import com.example.help_b.model.QuestionDto;
import com.example.help_b.service.QuestionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Security;
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
                        @RequestParam(name = "id" ,required = false) Integer id,
                        HttpServletRequest request,
                        Model model){
//        BasicUser basicUser= (BasicUser) request.getSession().getAttribute("user");
        BasicUser basicUser = (BasicUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        question.setAuthor(basicUser.getId());
        question.setCreatedAt(simpleDateFormat.format(new Date()));
        question.setModifiedAt(question.getCreatedAt());
        System.out.println(question);
        if(id!=null){
            question.setId(id);
            questionService.updateQuestion(question);
        }else {
            questionService.insertQuestion(question);
        }
        return "redirect:/";
    }
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id")Integer id,
                       Model model){
        QuestionDto questionDto = questionService.selectQuestionById(id);
        if(SecurityContextHolder.getContext().getAuthentication().getName().equals(questionDto.getBasicUser().getUsername())){
            model.addAttribute("question",questionDto);
            return "publish";
        }else {
            return "index";
        }

    }
}
