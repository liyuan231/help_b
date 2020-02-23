package com.example.help_b.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
