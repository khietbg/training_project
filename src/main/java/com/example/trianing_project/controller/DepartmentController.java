package com.example.trianing_project.controller;

import com.example.trianing_project.service.DepartmentService;
import com.example.trianing_project.service.dto.DepartmentDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String index(@RequestParam(name = "search", required = false, defaultValue = "") String search, Pageable pageable, @ModelAttribute("message") String message, Model model) {
        model.addAttribute("message", message);
        model.addAttribute("departments", departmentService.findAllByName(search, pageable));
        model.addAttribute("page", pageable);
        return "department/index";
    }

    @GetMapping("/add")
    public String showAdd(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("message", message);
        model.addAttribute("department", new DepartmentDTO());
        model.addAttribute("parents", departmentService.findAll());
        return "department/add";
    }

    @PostMapping("/add")
    public String doAdd(@Valid @ModelAttribute("department") DepartmentDTO departmentDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("department", departmentDTO);
            model.addAttribute("parents", departmentService.findAll());
            return "department/add";
        }
        Optional<DepartmentDTO> department = departmentService.findByDepartmentCode(departmentDTO.getDepartmentCode());
        if (department.isPresent()) {
            redirectAttributes.addFlashAttribute("message", "Department ton tai");
            return "redirect:/departments/add";
        }
        departmentService.save(departmentDTO);
        return "redirect:/departments/index";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable("id") Long id, Model model, @ModelAttribute("message") String message, RedirectAttributes redirectAttributes) {
        Optional<DepartmentDTO> departmentDTO = departmentService.findOne(id);
        if (!departmentDTO.isPresent()) {
            redirectAttributes.addFlashAttribute("message", "No Content!!!");
            return "redirect:/departments/index";
        }
        model.addAttribute("message", message);
        model.addAttribute("parents", departmentService.findAll());
        model.addAttribute("department", departmentDTO.get());
        return "department/edit";
    }

    @PostMapping("/edit")
    public String doEdit(@ModelAttribute("department") DepartmentDTO departmentDTO, @Valid BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        Optional<DepartmentDTO> department = departmentService.findByDepartmentCode(departmentDTO.getDepartmentCode());
        if (bindingResult.hasErrors()) {
            model.addAttribute("department", departmentDTO);
            model.addAttribute("parents", departmentService.findAll());
            return "department/edit";
        }
        if (department.isPresent() && !department.get().getId().equals(departmentDTO.getId())) {
            redirectAttributes.addFlashAttribute("message", "Department Code is already in use!!!");
            return "redirect:/departments/edit/" + departmentDTO.getId();
        }
        departmentService.save(departmentDTO);
        return "redirect:/departments/index";
    }

    @GetMapping("/delete/{id}")
    public String doDelete(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        if (!departmentService.findOne(id).isPresent()) {
            redirectAttributes.addFlashAttribute("message", "No Content!!!");
            return "redirect:/departments/index";
        }
        if (departmentService.existsByParentId(id)) {
            redirectAttributes.addFlashAttribute("message", "remove the element belonging to this element first!!!");
            return "redirect:/departments/index";
        }
        departmentService.delete(id);
        return "redirect:/departments/index";

    }
}
