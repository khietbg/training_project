package com.example.trianing_project.controller;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

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
        return "project/add";
    }

    @PostMapping("/add")
    public String doAdd(@ModelAttribute("project") @Valid ProjectDTO projectDTO, @RequestParam("employees") List<Long> employees, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "project/add";
        }
        // chuyển từ id thành đối tượng
        for (Long id : employees) {
            Optional<EmployeeDTO> employeeDTO = employeeService.findOne(id);
            if (employeeDTO.isPresent()) {
                projectDTO.getEmployeeIds().add(employeeDTO.get());
            }
        }
        projectDTO.setPmId(1L);
        projectService.save(projectDTO);
        redirectAttributes.addFlashAttribute("projectDTO", projectDTO);
        return "redirect:/project/index";
    }

    @PostMapping("/edit")
    public String doEdit(@Valid @ModelAttribute("project") ProjectDTO projectDto, @RequestParam("employees") List<Long> employees, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employees", employeeService.findAll());
            return "project/edit";
        }
        for (Long id : employees) {
            Optional<EmployeeDTO> employeeDTO = employeeService.findOne(id);
            if (employeeDTO.isPresent()) {
                projectDto.getEmployeeIds().add(employeeDTO.get());
            }
        }
        projectDto.setPmId(getUserId());
        projectService.save(projectDto);
        return "redirect:/project/index";//+ getUserId()
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable("id") Long id, Model model, HttpSession session) {
        Optional<ProjectDTO> projectDto = projectService.findOne(id);
        model.addAttribute("employees", employeeService.findAll());
        if (!projectDto.isPresent()) {
            return "redirect:/project/index";
        }
        List<EmployeeDTO> selectedEmployees = (List<EmployeeDTO>) session.getAttribute("selectedEmployees");
        if (selectedEmployees != null && !selectedEmployees.isEmpty()) {
            model.addAttribute("selectedEmployees", selectedEmployees);
        }
        if (projectDto.get().getPmId() != getUserId()) {
            return "redirect:/project/index";
        }
        model.addAttribute("project", projectDto.get());
        return "project/edit";
    }

    @GetMapping("/delete/{id}")
    public String doDelete(@PathVariable("id") Long id) {
        if (!projectRepository.existsById(id)) {
            return "redirect:/project/index"; //+ getUserId()
        }
        projectService.delete(id);
        return "redirect:/project/index";//+ getUserId()
    }

    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        Optional<ProjectDTO> projectDto = projectService.findOne(id);
        model.addAttribute("employeeProject", employeeService.findByProjectId(id));
        model.addAttribute("project", projectDto.get());
        return "project/detail";
    }

    public Long getUserId() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return employeeService.findEmployeeByEmail(authentication.getName()).getId();
        return 1L;
    }
}
