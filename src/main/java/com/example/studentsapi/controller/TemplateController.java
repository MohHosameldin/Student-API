package com.example.studentsapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class TemplateController {

    @GetMapping("/login")
    public String getLoginView() {
        return "login"; 
    }
     @GetMapping("/dashboard.html")
    public String getDashboardView() {
        return "dashboard"; 
    }
    
}