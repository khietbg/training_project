package com.example.trianing_project.controller;

import com.example.trianing_project.domain.Employee;
import com.example.trianing_project.repository.EmployeeRepository;
import com.example.trianing_project.service.EmployeeService;
import com.example.trianing_project.service.dto.EmployeeDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/findAll")
    public String findAll(@RequestParam(name = "textSearch", required = false, defaultValue = "") String textSearch, Pageable pageable) {

        return "employee/employees";
    }

    @GetMapping("/add")
    public String showAdd(Model model) {
        model.addAttribute("employee", new EmployeeDTO());
        return "employee/add";
    }

    @PostMapping("/Add")
    public String doAdd(@ModelAttribute EmployeeDTO employeeDTO, String rePassword, BindingResult bindingResult, Model model, HttpSession session) {
        EmployeeDTO employeeLogin = (EmployeeDTO) session.getAttribute("employeeLogin");
        employeeDTO.setManagerId(employeeLogin.getId());
        if (employeeDTO.getPassword().equals(rePassword)) {
            model.addAttribute("phone", "phone number invalid");
            return "employee/add";
        }
        if (bindingResult.hasErrors()) {
            return "employee/add";
        }
        if (employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            model.addAttribute("email", "email existed please try a gain! ");
            return "employee/add";
        }
        employeeService.save(employeeDTO);
        return "redirect:findAll";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        employeeService.delete(id);
        return "redirect:findAll";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.findOne(id));
        return "employee/edit";
    }

    @PostMapping("edit")
    public String doEdit(@Valid @ModelAttribute("employee") EmployeeDTO employeeDTO, BindingResult bindingResult) {
        return "redirect:findAll";
    }

}
