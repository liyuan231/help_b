package com.example.help_b.component.exception;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ExceptionAttributes extends DefaultErrorAttributes {
    private static final String ERROR_ATTRIBUTE = DefaultErrorAttributes.class.getName() + ".ERROR";
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
//        return super.getErrorAttributes(webRequest, includeStackTrace);
        Map<String, Object> errorAttributes = new LinkedHashMap();
        String path = (String)webRequest.getAttribute("javax.servlet.error.request_uri",0);
        Integer status = (Integer)webRequest.getAttribute("javax.servlet.error.status_code",0);
//        Throwable
        Throwable ex = (Throwable) webRequest.getAttribute(ERROR_ATTRIBUTE,0);
        errorAttributes.put("timestamp", new Date());
        errorAttributes.put("path",path);
        errorAttributes.put("status",status);
        if (ex==null){
            ex = (Throwable) webRequest.getAttribute("javax.servlet.error.exception",0);
        }
        if (ex!=null){
            errorAttributes.put("message",ex.getMessage());
//                errorAttributes.put("code", ((MyException) ex).getCode());
        }
        return errorAttributes;
    }

}
