package com.example.trianing_project.controller;

import com.example.trianing_project.domain.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping({"/","/login"})
    public String login() {
        return "login";
    }
    @GetMapping("/add")
    public String showAdd(Model model){
        model.addAttribute("employee",new Employee());
        return "add";
    }
}
