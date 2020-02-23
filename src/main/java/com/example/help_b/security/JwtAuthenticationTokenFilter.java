package com.example.help_b.security;

import com.example.help_b.component.Utils;
import com.example.help_b.model.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource(name = "utils")
    Utils utils;
    @Value("${token.header}")
    private String tokenHeader;
    @Value("${token.header.startWith}")
    private String tokenHeaderStartWith;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorization = httpServletRequest.getHeader(tokenHeader);
        if (authorization==null){
            Cookie[] cookies =httpServletRequest.getCookies();
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    authorization = cookie.getValue();
                    break;
                }

            }
        }
        if(authorization!=null){
            if(authorization.startsWith(tokenHeaderStartWith)){
                authorization = authorization.substring(tokenHeaderStartWith.length());
            }
            SysUser sysUser = utils.getUserFromToken(authorization);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysUser,null,sysUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }
}
