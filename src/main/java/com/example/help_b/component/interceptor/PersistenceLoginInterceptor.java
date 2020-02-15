package com.example.help_b.component.interceptor;


import com.auth0.jwt.JWT;
import com.example.help_b.model.BasicUser;
import com.example.help_b.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PersistenceLoginInterceptor implements HandlerInterceptor {
    @Resource(name = "userServiceImpl")
    UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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
        return true;
    }
}
