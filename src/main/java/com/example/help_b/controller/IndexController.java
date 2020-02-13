package com.example.help_b.controller;

import com.auth0.jwt.JWT;
import com.example.help_b.model.BasicUser;
import com.example.help_b.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Resource(name = "userServiceImpl")
    UserService userService;
    @GetMapping("/")
    public String index(HttpServletRequest request){
        if(request.getSession().getAttribute("user")==null){
            Cookie[]cookies=request.getCookies();
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    String id= JWT.decode(cookie.getValue()).getAudience().get(0);
                    BasicUser basicUser = userService.selectGitHubUserById(Integer.valueOf(id));
                    request.getSession().setAttribute("user",basicUser);
                    break;
                }
            }
        }
        return "index";
    }
}
