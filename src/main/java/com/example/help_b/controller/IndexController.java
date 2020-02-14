package com.example.help_b.controller;

import com.auth0.jwt.JWT;
import com.example.help_b.model.BasicUser;
import com.example.help_b.model.Question;
import com.example.help_b.model.QuestionDto;
import com.example.help_b.service.QuestionService;
import com.example.help_b.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Resource(name = "userServiceImpl")
    UserService userService;
    @Resource(name = "questionServiceImpl")
    QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model){
        if(request.getSession().getAttribute("user")==null){
            Cookie[]cookies=request.getCookies();
            if (cookies != null) {
                for(Cookie cookie:cookies){
                    if(cookie.getName().equals("token")){
                        String id= JWT.decode(cookie.getValue()).getAudience().get(0);
                        BasicUser basicUser = userService.selectGitHubUserById(Integer.valueOf(id));
                        request.getSession().setAttribute("user",basicUser);
                        break;
                    }
                }
            }
           }
        List<QuestionDto> questions = questionService.getQuestions(1);
        model.addAttribute("questions",questions);
        return "index";
    }
}
