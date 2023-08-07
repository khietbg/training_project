package com.example.trianing_project.controller;

import com.example.trianing_project.service.DepartmentService;
import com.example.trianing_project.service.EmployeeService;
import com.example.trianing_project.service.SkillService;
import com.example.trianing_project.service.dto.DepartmentDTO;
import com.example.trianing_project.service.dto.EmployeeDTO;
import com.example.trianing_project.service.dto.GetData;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthController {
    private final SkillService skillService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public AuthController(SkillService skillService, EmployeeService employeeService, DepartmentService departmentService) {
        this.skillService = skillService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }
    @RequestMapping("/")
    public String formLogin(){
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeeDTO employeeLogin = employeeService.findEmployeeByEmail(authentication.getName());
        List<DepartmentDTO> dtos = departmentService.findAll();
        List<EmployeeDTO> employeeDTOList = employeeService.findAll();
        List<Integer> data = new ArrayList<>();
        List<String> name = new ArrayList<>();
        for (GetData d: skillService.getDataByName()) {
            data.add(d.getData());
            name.add(d.getName());
        }
        model.addAttribute("name",name);
        model.addAttribute("data", data);
        model.addAttribute("employee", employeeLogin);
        model.addAttribute("sumE", employeeDTOList.size());
        model.addAttribute("sumd", dtos.size());
        return "layout/index";
    }
    @RequestMapping("/403")
    public String errorPage(){
        return "/403";
    }
}
