package com.example.trianing_project.controller;

import com.example.trianing_project.repository.ProjectRepository;
import com.example.trianing_project.service.ProjectService;
import com.example.trianing_project.service.dto.ProjectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectRepository projectRepository;

    public ProjectController(ProjectService projectService, ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(name = "name", required = false, defaultValue = "") String name, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/project/index");
        Page<ProjectDto> projectDtos = projectService.findAll(name, pageable);
        modelAndView.addObject("projectDto", projectDtos);
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView doAdd(@Valid @ModelAttribute("project") ProjectDto projectDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("project/add",bindingResult.getModel());
            return modelAndView;
        }
        projectService.save(projectDto);
        return new ModelAndView("redirect:/project/index");
    }

    @GetMapping("/add")
    public ModelAndView showAdd() {
        ModelAndView modelAndView = new ModelAndView("project/add");
        modelAndView.addObject("project", new ProjectDto());
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("project/edit");
        Optional<ProjectDto> projectDto = projectService.findOne(id);
        if (projectDto.isPresent()) {
            return modelAndView.addObject("project", projectDto.get());
        }
        return new ModelAndView("project/index");
    }

    @PostMapping("/edit")
    public ModelAndView doEdit(@Valid @ModelAttribute("project") ProjectDto projectDto,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("project/edit", bindingResult.getModel());
            return modelAndView;
        }
        projectService.save(projectDto);
        ModelAndView modelAndView = new ModelAndView("redirect:/project/index");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView doDelete(@PathVariable("id") Long id) {
        if (!projectRepository.existsById(id)) {
            return new ModelAndView("project/index");
        }
        projectService.delete(id);
        return new ModelAndView("redirect:/project/index");
    }
    @GetMapping("/detail/{id}")
    private ModelAndView showDetail(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("project/detail");
        Optional<ProjectDto> projectDto = projectService.findOne(id);
        if (projectDto.isPresent()) {
            modelAndView.addObject("project", projectDto.get());
            return modelAndView;
        }
        return new ModelAndView("project/index");
    }
}
