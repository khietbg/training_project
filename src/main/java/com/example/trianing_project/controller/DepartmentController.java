package com.example.trianing_project.controller;

import com.example.trianing_project.service.DepartmentService;
import com.example.trianing_project.service.dto.DepartmentDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;

    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(name = "search", required = false, defaultValue = "") String search, Pageable pageable, @ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView("department/index");
        modelAndView.addObject("message", message);
        modelAndView.addObject("departments", departmentService.findAllByName(search, pageable));
        modelAndView.addObject("page", pageable);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView showAdd() {
        ModelAndView modelAndView = new ModelAndView("department/add");
        modelAndView.addObject("department", new DepartmentDTO());
        modelAndView.addObject("parents", departmentService.findAll());
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView doAdd(@Valid @ModelAttribute("department") DepartmentDTO departmentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("department/add", bindingResult.getModel());
            modelAndView.addObject("department", departmentDTO);
            modelAndView.addObject("parents", departmentService.findAll());
            return modelAndView;
        }
        Optional<DepartmentDTO> department = departmentService.findByDepartmentCode(departmentDTO.getDepartmentCode());
        if (department.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("redirect:/departments/add");
            modelAndView.addObject("department", departmentDTO);
            modelAndView.addObject("massage", "Department Code is already in use!!!");
            modelAndView.addObject("parents", departmentService.findAll());
            return modelAndView;
        }
        departmentService.save(departmentDTO);
        return new ModelAndView("redirect:/departments/index?page=0&&size=5");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable("id") Long id) {
        Optional<DepartmentDTO> departmentDTO = departmentService.findOne(id);
        if (!departmentDTO.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("redirect:/departments/index?page=0&&size=5");
            modelAndView.addObject("message", "No Content!!!");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("department/edit");
        modelAndView.addObject("parents", departmentService.findAll());
        modelAndView.addObject("department", departmentDTO.get());
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView doEdit(@ModelAttribute("department") DepartmentDTO departmentDTO, @Valid BindingResult bindingResult) {
        Optional<DepartmentDTO> department = departmentService.findByDepartmentCode(departmentDTO.getDepartmentCode());
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("department/edit", bindingResult.getModel());
            modelAndView.addObject("parents", departmentService.findAll());
            modelAndView.addObject("department", new DepartmentDTO());
            return modelAndView;
        }
        if (department.isPresent() && !department.get().getId().equals(departmentDTO.getId())) {
            ModelAndView modelAndView = new ModelAndView("department/edit", bindingResult.getModel());
            modelAndView.addObject("massage", "Department Code is already in use!!!");
            modelAndView.addObject("parents", departmentService.findAll());
            modelAndView.addObject("department", new DepartmentDTO());
            return modelAndView;
        }
        departmentService.save(departmentDTO);
        return new ModelAndView("redirect:/departments/index?page=0&&size=5");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView doDelete(@PathVariable("id") Long id) {
        if (!departmentService.findOne(id).isPresent()) {
            ModelAndView modelAndView = new ModelAndView("redirect:/departments/index?page=0&&size=5");
            modelAndView.addObject("message", "No Content!!!");
            return new ModelAndView();
        }
        if (departmentService.existsByParentId(id)) {
            ModelAndView modelAndView = new ModelAndView("redirect:/departments/index?page=0&&size=5");
            modelAndView.addObject("message", "remove the element belonging to this element first!!!");
            return modelAndView;
        }
        departmentService.delete(id);
        return new ModelAndView("redirect:/departments/index?page=0&&size=5");

    }
}
