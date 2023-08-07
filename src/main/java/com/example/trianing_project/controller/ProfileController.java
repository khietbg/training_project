package com.example.trianing_project.controller;

import com.example.trianing_project.service.*;
import com.example.trianing_project.service.dto.EmployeeDTO;
import com.example.trianing_project.service.email.SendEmailService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/profile")
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
public class ProfileController {
    private final EmployeeService employeeService;
    private final ProjectService projectService;
    private final ExperienceService experienceService;
    private final CertificateService certificateService;
    private final PasswordEncoder passwordEncoder;
    private final SendEmailService sendEmailService;
    private final SkillService skillService;

    public ProfileController(EmployeeService employeeService, ProjectService projectService, ExperienceService experienceService, CertificateService certificateService, PasswordEncoder passwordEncoder, SendEmailService sendEmailService, SkillService skillService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
        this.experienceService = experienceService;
        this.certificateService = certificateService;
        this.passwordEncoder = passwordEncoder;
        this.sendEmailService = sendEmailService;
        this.skillService = skillService;
    }

    @GetMapping("/index")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeeDTO employeeLogin = employeeService.findEmployeeByEmail(authentication.getName());
        model.addAttribute("skills", skillService.findAllByEmployeeId(employeeLogin.getId()));
        model.addAttribute("projects", employeeLogin.getProjects());
        model.addAttribute("experiences", experienceService.findAllByEmployeeId(employeeLogin.getId()));
        model.addAttribute("certificates", certificateService.findAllByEmployeeId(employeeLogin.getId()));
        model.addAttribute("employee", employeeLogin);
        return "profile/index";
    }
    @GetMapping("/changePassword")
    public String formChange(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeeDTO employeeLogin = employeeService.findEmployeeByEmail(authentication.getName());
        model.addAttribute("employee",employeeLogin);
        return "profile/change-password";
    }

    @PostMapping("/changePassword")
    public String changePassword(Model model, @RequestParam("oldPass") String oldPass, @RequestParam("newPass") String newPass, @RequestParam("rePass") String repass) throws MessagingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeeDTO employeeLogin = employeeService.findEmployeeByEmail(authentication.getName());
        if (!passwordEncoder.matches(oldPass, employeeLogin.getPassword())) {
            model.addAttribute("message", "old password not match!");
            return "profile/change-password";
        }
        if (!newPass.equals(repass)) {
            model.addAttribute("message", "re password not match!");
            return "profile/change-password";
        }
        employeeLogin.setPassword(newPass);
        sendEmailService.sendMailChangePassword(employeeLogin);
        employeeLogin.setPassword(passwordEncoder.encode(employeeLogin.getPassword()));
        employeeService.update(employeeLogin);
        model.addAttribute("message", "change password success!");
        return "/login";
    }
}
