package com.example.help_b.controller;

import com.example.help_b.dao.UserDao;
import com.example.help_b.model.AccessToken;
import com.example.help_b.model.GitHubUser;
import com.example.help_b.service.GitHubService;
import com.example.help_b.service.TokenService;
import com.example.help_b.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class OAuthController {
    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;
    @Value("${github.redirect.uri}")
    private String redirect_uri;
    @Resource(name = "userServiceImpl")
    UserService userService;
    @Resource(name = "tokenServiceImpl")
    TokenService tokenService;
    /**
     * 此处是GitHubUser登录
     */
    @Resource(name = "gitHubServiceImpl")
    GitHubService gitHubService;
    @GetMapping("/callback")
    public String callback(@RequestParam("code")String code,
                           @RequestParam("state")String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        String accessToken=gitHubService.getAccessToken(new AccessToken(client_id,client_secret,code,redirect_uri,state));
        GitHubUser gitHubUser=gitHubService.getGitHubUser(accessToken);
        if(gitHubUser!=null){
            userService.insertGitHubUser(gitHubUser);
            response.addCookie(new Cookie("token",tokenService.getToken(gitHubUser)));
            request.getSession().setAttribute("user",gitHubUser);
        }else{

        }
        return "redirect:/";
        //使用okhttp进行post请求
    }
}
