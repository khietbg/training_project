package com.example.trianing_project.controller;

import com.example.trianing_project.repository.EmployeeRepository;
import com.example.trianing_project.service.DepartmentService;
import com.example.trianing_project.service.EmployeeService;
import com.example.trianing_project.service.dto.EmployeeDTO;
import com.example.trianing_project.service.email.SendEmailService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;
    private final SendEmailService mailService;
    private final DepartmentService departmentService;

    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository, SendEmailService mailService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
        this.mailService = mailService;
        this.departmentService = departmentService;
    }

    @GetMapping("/index")
    public String findAll(@RequestParam(name = "textSearch", required = false, defaultValue = "") String textSearch, Pageable pageable, Model model) {
        model.addAttribute("page", employeeService.findAll(textSearch, pageable));
        return "employee/index";
    }

    @GetMapping("/add")
    public String showAdd(Model model) {
        model.addAttribute("employee", new EmployeeDTO());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("departments", departmentService.findAll());
        return "employee/add";
    }

    @PostMapping("/add")
    public String doAdd(@Valid @ModelAttribute("department") EmployeeDTO employeeDTO, BindingResult bindingResult, Model model) throws MessagingException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employee", employeeDTO);
            model.addAttribute("employees", employeeService.findAll());
            model.addAttribute("departments", departmentService.findAll());
            return "employee/add";
        }
        mailService.sendMail(employeeDTO);
        employeeService.save(employeeDTO);
        return "redirect:/employee/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        employeeService.delete(id);
        return "redirect:/employee/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.findOne(id).get());
        model.addAttribute("departments", departmentService.findAll());
        return "employee/edit";
    }

    @PostMapping("/edit")
    public String doEdit(@ModelAttribute("employee") EmployeeDTO employeeDTO) {
        employeeService.save(employeeDTO);
        return "redirect:/employee/index";
    }
}
