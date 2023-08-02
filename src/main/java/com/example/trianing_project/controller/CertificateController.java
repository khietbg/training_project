package com.example.trianing_project.controller;

import com.example.trianing_project.service.CertificateService;
import com.example.trianing_project.service.EmployeeService;
import com.example.trianing_project.service.dto.CertificateDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/employee/certificate")
public class CertificateController {
    private final EmployeeService employeeService;
    private final CertificateService certificateService;

    public CertificateController(EmployeeService employeeService, CertificateService certificateService) {
        this.employeeService = employeeService;
        this.certificateService = certificateService;
    }
    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable("id")Long id,Model model){
        Optional<CertificateDTO> certificateDTO = certificateService.findOne(id);
        model.addAttribute("certificate",certificateDTO.get());
        return "certificate/detail";
    }
    @GetMapping("/add")
    public String showAdd(Model model) {
        model.addAttribute("certificate", new CertificateDTO());
        return "certificate/add";
    }

    @PostMapping("/add")
    public String doAdd(@ModelAttribute("certificate") CertificateDTO certificateDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("certificate", new CertificateDTO());
            return "certificate/add";
        }
        certificateDTO.setEmployeeId(getUserId());
        certificateService.save(certificateDTO);
        return "redirect:/employee/index";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, Model model) {
        Optional<CertificateDTO> certificateDTO = certificateService.findOne(id);
        if (!certificateDTO.isPresent()) {
            redirectAttributes.addFlashAttribute("message", "No content!!!");
            return "redirect:/employee/index";
        }
        if (certificateDTO.get().getEmployeeId() != getUserId()) {
            return "redirect:/employee/index";
        }
        model.addAttribute("certificate", certificateDTO.get());
        return "certificate/edit";
    }

    @PostMapping("/edit")
    public String doEdit(@ModelAttribute("certificate") CertificateDTO certificateDTO, @Valid BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/employee/certificate/edit/" + certificateDTO.getId();
        }
        certificateService.save(certificateDTO);
        return "redirect:/employee/edit/" + getUserId();
    }

    @GetMapping("/delete/{id}")
    public String doDelete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<CertificateDTO> certificateDTO = certificateService.findOne(id);
        if (!certificateDTO.isPresent()) {
            redirectAttributes.addFlashAttribute("message", "No Content");
            return "redirect:/employee/edit/" + getUserId();
        }
        certificateService.delete(id);
        return "redirect:/employee/edit/" + getUserId();
    }

    public Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return employeeService.findEmployeeByEmail(authentication.getName()).getId();
    }
}
