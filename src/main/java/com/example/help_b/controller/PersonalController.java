package com.example.help_b.controller;

import com.example.help_b.model.BasicUser;
import com.example.help_b.model.Page;
import com.example.help_b.model.QuestionDto;
import com.example.help_b.service.PageService;
import com.example.help_b.service.QuestionService;
import com.example.help_b.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PersonalController {
    @Value("${page.questions.size}")
    private  Integer size;
    @Resource(name = "pageServiceImpl")
    PageService pageService;

    @Resource(name = "userServiceImpl")
    UserService userService;
    @Resource(name = "questionServiceImpl")
    QuestionService questionService;
    @GetMapping("/personal/{action}")
    public String personal(@PathVariable("action")String action,
                           Model model,
                           HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(action.equals("questions")){
            model.addAttribute("action","questions");
            model.addAttribute("actionName","我的问题");
            BasicUser user= (BasicUser) authentication.getPrincipal();
            List<QuestionDto> questions = questionService.selectPersonalQuestions(1,size,user.getId().toString());
            model.addAttribute("questions",questions);
            Page pageInfo= pageService.wrestleWithPage(1,questionService.sum(user.getId().toString()),size);
            model.addAttribute("page",pageInfo);
        }else if(action.equals("latestReplies")){
            model.addAttribute("action","latestReplies");
            model.addAttribute("actionName","最新回复");
            BasicUser user= (BasicUser) authentication.getPrincipal();
            List<QuestionDto> questions = questionService.selectPersonalQuestions(1,size,user.getId().toString());
            model.addAttribute("questions",questions);
            Page pageInfo= pageService.wrestleWithPage(1,questionService.sum(user.getId().toString()),size);
            model.addAttribute("page",pageInfo);
        }
        return "personal";
    }
    @GetMapping("personal/questions/{page}")
    public String personalQuestions(@PathVariable("page") Integer page,
                                    Model model,
                                    HttpServletRequest request){
        model.addAttribute("action","questions");
        model.addAttribute("actionName","我的问题");
        BasicUser user=userService.selectSysUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user==null){
            user = userService.selectGitHubUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        }

        List<QuestionDto> questionDtos = questionService.selectPersonalQuestions(page,size,user.getId().toString());
        model.addAttribute("questions",questionDtos);
        Page pageInfo = pageService.wrestleWithPage(page,questionService.sum(user.getId().toString()),size);
        model.addAttribute("page",pageInfo);
        return "personal";
    }
    @GetMapping("personal/latestReplies/{page}")
    public String latestReplies(@PathVariable("page")Integer page,
                                Model model,
                                HttpServletRequest request){
        model.addAttribute("action","latestReplies");
        model.addAttribute("actionName","最新回复");
        BasicUser user= (BasicUser) request.getSession().getAttribute("user");
        List<QuestionDto> questionDtos = questionService.selectPersonalQuestions(page,size,user.getId().toString());
        model.addAttribute("questions",questionDtos);
        Page pageInfo = pageService.wrestleWithPage(page,questionService.sum(user.getId().toString()),size);
        model.addAttribute("page",pageInfo);
        return "personal";
    }
}
