package com.example.trianing_project.controller;

import com.example.trianing_project.domain.Employee;
import com.example.trianing_project.repository.ProjectRepository;
import com.example.trianing_project.service.EmployeeService;
import com.example.trianing_project.service.ProjectService;
import com.example.trianing_project.service.dto.EmployeeDTO;
import com.example.trianing_project.service.dto.ProjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectRepository projectRepository;
    private final EmployeeService employeeService;

    public ProjectController(ProjectService projectService, ProjectRepository projectRepository, EmployeeService employeeService) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
        this.employeeService = employeeService;
    }

    @GetMapping("/index")
    public String index(@RequestParam(name = "name", required = false, defaultValue = "") String name, Pageable pageable, Model model) {
        Page<ProjectDTO> projectDtos = projectService.findAll(name, pageable);
        model.addAttribute("projectDto", projectDtos);
        return "/project/index";
    }

    @GetMapping("/add")
    public String showAdd(Model model, HttpSession session) {
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("project", new ProjectDTO());
        List<EmployeeDTO> selectedEmployees = (List<EmployeeDTO>) session.getAttribute("selectedEmployees");
        if (selectedEmployees != null && !selectedEmployees.isEmpty()) {
            model.addAttribute("selectedEmployees", selectedEmployees);
        }
        return "project/add";
    }

    @PostMapping("/add")
    public String doAdd(@Valid @ModelAttribute("project") ProjectDTO projectDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employees", employeeService.findAll());
            return "project/add";
        }
        projectDto.setPmId(getUserId());
        projectService.save(projectDto);
        return "redirect:/project/index";
    }


    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable("id") Long id, Model model) {
        Optional<ProjectDTO> projectDto = projectService.findOne(id);
        model.addAttribute("employees", employeeService.findAll());
        if (!projectDto.isPresent()) {
            return "redirect:/project/index";
        }

        model.addAttribute("project", projectDto.get());
        return "project/edit";
    }

    @PostMapping("/edit")
    public String doEdit(@Valid @ModelAttribute("project") ProjectDTO projectDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "project/edit";
        }
        projectService.save(projectDto);
        return "redirect:/project/index";
    }

    @GetMapping("/delete/{id}")
    public String doDelete(@PathVariable("id") Long id) {
        if (!projectRepository.existsById(id)) {
            return "redirect:/project/index";
        }
        projectService.delete(id);
        return "redirect:/project/index";
    }

    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        Optional<ProjectDTO> projectDto = projectService.findOne(id);
        if (projectDto.isPresent()) {
            model.addAttribute("project", projectDto.get());
            return "project/detail";
        }
        return "redirect:/project/index";
    }


    @GetMapping("/show/employee")
    public String indexE(@RequestParam(required = false, defaultValue = "") String textSearch,
                         Pageable pageable, Model model) {
        Page<EmployeeDTO> listOfEmployees = employeeService.findAll(textSearch, pageable);
        model.addAttribute("listOfEmployees", listOfEmployees);
        return "project/employee_create";
    }

    @PostMapping("/add/employee")
    public String doAdd(@RequestParam(value = "selectedEmployeeCodes", required = false) List<String> selectedEmployees,
                        HttpSession session, Model model) {
        if (selectedEmployees == null || selectedEmployees.isEmpty()) {
            return "project/employee_create";
        }
        List<EmployeeDTO> selectedEmployee = new ArrayList<>();
        for (String employeeCode : selectedEmployees) {
            Long employeeId = Long.parseLong(employeeCode);
            Optional<EmployeeDTO> employee = employeeService.findOne(employeeId);
            if (employee.isPresent()) {
                selectedEmployee.add(employee.get());
            }
        }
        session.setAttribute("selectedEmployees", selectedEmployee);

        return "redirect:/project/add";
    }

    @GetMapping("/employee/delete/{id}")
    public String doDelete(@PathVariable Long id, HttpSession session, Model model) {
        List<EmployeeDTO> selectedEmployees = (List<EmployeeDTO>) session.getAttribute("selectedEmployees");
        if (selectedEmployees != null) {
            selectedEmployees.removeIf(employee -> employee.getId().equals(id));
            session.setAttribute("selectedEmployees", selectedEmployees);
        }
        model.addAttribute("selectedEmployees", selectedEmployees);
        return "redirect:/project/add";
    }

    public Long getUserId() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return employeeService.findEmployeeByEmail(authentication.getName()).getId();
        return 1L;
    }
}
