package com.example.trianing_project.controller;

import com.example.trianing_project.service.EmployeeService;
import com.example.trianing_project.service.ExperienceService;
import com.example.trianing_project.service.dto.EmployeeDTO;
import com.example.trianing_project.service.dto.ExperienceDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/experience")
public class ExperienceController {
    private final ExperienceService experienceService;
    private final EmployeeService employeeService;

    public ExperienceController(ExperienceService experienceService, EmployeeService employeeService) {
        this.experienceService = experienceService;
        this.employeeService = employeeService;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeeDTO employeeLogin = employeeService.findEmployeeByEmail(authentication.getName());
        experienceDTO.setEmployeeId(employeeLogin.getId());
        experienceService.save(experienceDTO);
        return "redirect:/profile/index";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("experience", experienceService.findOne(id).get());
        return "/experience/edit";
    }

    @PostMapping("/edit")
    public String doEdit(@ModelAttribute ExperienceDTO experienceDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeeDTO employeeLogin = employeeService.findEmployeeByEmail(authentication.getName());
        experienceDTO.setEmployeeId(employeeLogin.getId());
        experienceService.save(experienceDTO);
        return "redirect:/profile/index";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        experienceService.delete(id);
        return "redirect:/profile/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("experience", experienceService.findOne(id).get());
        return "/experience/detail";
    }
}

