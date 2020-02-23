package com.example.help_b.config;

import com.example.help_b.component.interceptor.PersistenceLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
//@EnableWebMvc //开启此注解就不会自动装配WebMvcAutoConfiguration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource(name = "persistenceLoginInterceptor")
    PersistenceLoginInterceptor persistenceLoginInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/publish").setViewName("publish");
        registry.addViewController("/login").setViewName("login");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(persistenceLoginInterceptor).addPathPatterns("/**");
//    }
}
