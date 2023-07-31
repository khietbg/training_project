package com.example.trianing_project.controller;

import com.example.trianing_project.service.ExperienceService;
import com.example.trianing_project.service.dto.ExperienceDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/experience")
public class ExperienceController {
    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("list", experienceService.findAll());
        return "/experience/index";
    }

    @GetMapping("/add")
    public String showAdd(Model model) {
        model.addAttribute("experience", new ExperienceDTO());
        return "/experience/add";
    }

    @PostMapping("/add")
    public String doAdd(@ModelAttribute("experience") ExperienceDTO experienceDTO) {
        experienceService.save(experienceDTO);
        return "redirect:/experience/index";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("experience", experienceService.findOne(id).get());
        return "/experience/edit";
    }

    @PostMapping("/edit")
    public String doEdit(@ModelAttribute ExperienceDTO experienceDTO) {
        experienceService.save(experienceDTO);
        return "redirect:/experience/index";
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id){
        experienceService.delete(id);
        return "redirect:/experience/index";
    }
}

