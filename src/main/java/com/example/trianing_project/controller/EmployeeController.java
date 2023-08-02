package com.example.trianing_project.controller;

import com.example.trianing_project.service.*;
import com.example.trianing_project.service.dto.EmployeeDTO;
import com.example.trianing_project.service.email.SendEmailService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final SendEmailService mailService;
    private final DepartmentService departmentService;
    private final SkillService skillService;
    private final ExperienceService experienceService;
    private final ProjectService projectService;
    private final CertificateService certificateService;
    private final PasswordEncoder passwordEncoder;

    public EmployeeController(EmployeeService employeeService, SendEmailService mailService, DepartmentService departmentService, SkillService skillService, ExperienceService experienceService, ProjectService projectService, CertificateService certificateService, PasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.mailService = mailService;
        this.departmentService = departmentService;
        this.skillService = skillService;
        this.experienceService = experienceService;
        this.projectService = projectService;
        this.certificateService = certificateService;
        this.passwordEncoder = passwordEncoder;
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

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("skills", skillService.findAllByEmployeeId(id));
        model.addAttribute("projects",projectService.findAllByEmployeeId(id));
        model.addAttribute("experiences", experienceService.findAllByEmployeeId(id));
        model.addAttribute("certificates", certificateService.findAllByEmployeeId(id));
        model.addAttribute("employee", employeeService.findOne(id).get());
        return "employee/detail";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeeDTO employeeLogin = employeeService.findEmployeeByEmail(authentication.getName());
        model.addAttribute("skills", skillService.findAllByEmployeeId(employeeLogin.getId()));
        model.addAttribute("projects",projectService.findAllByEmployeeId(employeeLogin.getId()));
        model.addAttribute("experiences", experienceService.findAllByEmployeeId(employeeLogin.getId()));
        model.addAttribute("certificates", certificateService.findAllByEmployeeId(employeeLogin.getId()));
        model.addAttribute("employee", employeeLogin);
        return "employee/profile";
    }

    @GetMapping("/updateProfile")
    public String formUpload(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeeDTO employeeLogin = employeeService.findEmployeeByEmail(authentication.getName());
        model.addAttribute("employee", employeeLogin);
        return "employee/updateProfile";
    }

    @PostMapping("/updateProfile")
    public String doUpload(@RequestParam("img") MultipartFile file, @ModelAttribute("employee") EmployeeDTO employeeDTO) throws IOException {
        if (!file.isEmpty()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            EmployeeDTO employeeLogin = employeeService.findEmployeeByEmail(authentication.getName());
            if (employeeLogin.getAvatarUrl() != null) {
                File oldImage = new File(employeeLogin.getAvatarUrl());
                if (oldImage.exists()) {
                    oldImage.delete();
                }
            }
            String uploadDir = "/Users/minhkhiet/Downloads/trianing_project/src/main/resources/static/assets/img";
            String fileName = file.getOriginalFilename();
            File uploadPath = new File(uploadDir);
            File targetFile = new File(uploadPath, fileName);
            file.transferTo(targetFile);
            employeeDTO.setAvatarUrl(fileName);
        }
        employeeService.save(employeeDTO);
        return "redirect:/employee/profile";
    }

    @GetMapping("/changePassword")
    public String formChange() {
        return "employee/changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(Model model,
                                 @RequestParam("oldPass") String oldPass,
                                 @RequestParam("newPass") String newPass,
                                 @RequestParam("rePass") String repass) throws MessagingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeeDTO employeeLogin = employeeService.findEmployeeByEmail(authentication.getName());
        if (!passwordEncoder.matches(oldPass,employeeLogin.getPassword())) {
            model.addAttribute("message","old password not match!");
            return "employee/changePassword";
        }if (!newPass.equals(repass)){
            model.addAttribute("message","re password not match!");
            return "employee/changePassword";
        }
        employeeLogin.setPassword(newPass);
        mailService.sendMailChangePassword(employeeLogin);
        employeeService.updatePassword(employeeLogin);
        model.addAttribute("message", "change password success!");
        return "/login";
    }

    @GetMapping("/back")
    public String back() {
        return "redirect:/employee/index";
    }
}
