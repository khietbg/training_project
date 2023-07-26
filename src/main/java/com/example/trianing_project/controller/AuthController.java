package com.example.trianing_project.controller;

import com.example.trianing_project.domain.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    @GetMapping("/")
    public String login() {
        return "login";
    }
}
