package com.example.help_b.component.handler;

import com.example.help_b.component.Utils;
import com.example.help_b.model.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Value("${token.expiration}")
    private Integer tokenExpiration;
    @Value("${token.header}")
    private String tokenHeader;
    @Value("${token.header.startWith}")
    private String tokenHeaderStartWith;
    @Resource(name = "utils")
    Utils utils;
//    @Resource(name = "tokenServiceImpl")
//    TokenService tokenService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token=utils.getToken((SysUser) authentication.getPrincipal());
        response.setHeader(tokenHeader,tokenHeaderStartWith+" "+token);
        Cookie cookie = new Cookie("token",token);
        cookie.setMaxAge(tokenExpiration);
        response.addCookie(cookie);
        System.out.println("onAuthenticationSuccess:"+SecurityContextHolder.getContext().getAuthentication());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
