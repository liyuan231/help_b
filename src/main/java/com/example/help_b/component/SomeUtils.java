package com.example.help_b.component;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class SomeUtils {
    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    }
}
