package com.example.trianing_project.controller;

import com.example.trianing_project.service.SkillService;
import com.example.trianing_project.service.dto.GetData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthController {
    private final SkillService skillService;

    public AuthController(SkillService skillService) {
        this.skillService = skillService;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<Integer> data = new ArrayList<>();
        List<String> name = new ArrayList<>();
        for (GetData d: skillService.getDataByName()) {
            data.add(d.getData());
            name.add(d.getName());
        }
        model.addAttribute("name",name);
        model.addAttribute("data", data);
        return "layout/index";
    }
}
