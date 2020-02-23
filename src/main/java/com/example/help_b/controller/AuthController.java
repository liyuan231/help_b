package com.example.help_b.controller;

import com.example.help_b.component.Utils;
import com.example.help_b.dao.UserDao;
import com.example.help_b.model.SysUser;
import com.example.help_b.service.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AuthController {
    @Value("${token.header}")
    private String tokenHeader;
    @Value("${token.header.startWith}")
    private String tokenStartWith;
    @Resource(name = "utils")
    Utils utils;
    @Resource(name = "userDao")
    UserDao userDao;
//    @Resource(name = "authenticationManager")
//    AuthenticationManager authenticationManager;
    @Resource(name = "simpleDateFormat")
    SimpleDateFormat simpleDateFormat;
    @Resource(name = "authServiceImpl")
    AuthService authService;
    @PostMapping("/register")
    /**
     * 默认所有用户刚注册时均为普通用户
     */
    public String register(@RequestParam("username")String username,
                           @RequestParam("password")String password) throws Exception {
        SysUser sysUser = new SysUser(username,password,simpleDateFormat.format(new Date()));
        sysUser=authService.register(sysUser);
        return "redirect:/";
    }
//    @PostMapping("/login")
//    public String login(@RequestParam("username")String username,
//                        @RequestParam("password")String password,
//                        HttpServletResponse response){
//        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        SysUser sysUser = (SysUser) authentication.getPrincipal();
//        sysUser.setRole(userDao.selectRoleByUserId(sysUser.getId()));
//        String token =utils.getAccessToken(sysUser);
//        response.addHeader("Authorization", "Bearer " + token);
//        response.addCookie(new Cookie("token",token));
//        return "index";
//    }

//    @GetMapping("/SysUser_logout")
//    public String logout(HttpServletRequest request,
//                         HttpServletResponse response){
//        String token = request.getHeader(tokenHeader);
//        if(token==null){
//            return "";
//        }else {
//            Cookie cookie = new Cookie("token","");
//            cookie.setMaxAge(0);
//            response.addCookie(new Cookie("token",""));
//            return "";
//        }
//    }
    @GetMapping("/refresh")
    public String refreshToken(HttpServletRequest request,
                               HttpServletResponse response){
        String token = request.getHeader(tokenHeader);
        if(token==null){
            Cookie[]cookies = request.getCookies();
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    token = cookie.getValue();
                    token=authService.refreshToken(token);
                }
                break;
            }
        }else {
            token=authService.refreshToken(token.substring(tokenStartWith.length()));
        }
        Cookie cookie = new Cookie("token","");
        cookie.setMaxAge(0);
        response.addCookie(new Cookie("token",""));
        return "redirect:/";
    }


}


