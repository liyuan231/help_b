package com.example.help_b.component.handler;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理的貌似是服务器端的错误
 */
//@ControllerAdvice
public class ErrorHandler {
//    @ExceptionHandler(value = QuestionException.class)
    public ModelAndView handleException(HttpServletRequest request,
                                        Throwable ex, Model model){
        HttpStatus status = getStatus(request);
        model.addAttribute("status");
        model.addAttribute("message",ex.getMessage());
        return new ModelAndView("error");
    }
    private HttpStatus getStatus(HttpServletRequest request){
        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(status==null){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(status);
    }

}
