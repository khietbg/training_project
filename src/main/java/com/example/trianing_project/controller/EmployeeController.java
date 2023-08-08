package com.example.trianing_project.controller;

import com.example.trianing_project.service.*;
import com.example.trianing_project.service.dto.EmployeeDTO;
import com.example.trianing_project.service.email.SendEmailService;
import com.lowagie.text.pdf.BaseFont;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;


import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

import java.io.ByteArrayOutputStream;


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
    private final TemplateEngine templateEngine;

    public EmployeeController(EmployeeService employeeService, SendEmailService mailService, DepartmentService departmentService, TemplateEngine templateEngine, SkillService skillService, ExperienceService experienceService, ProjectService projectService, CertificateService certificateService, PasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.mailService = mailService;
        this.departmentService = departmentService;
        this.skillService = skillService;
        this.experienceService = experienceService;
        this.projectService = projectService;
        this.certificateService = certificateService;
        this.passwordEncoder = passwordEncoder;
        this.templateEngine = templateEngine;
    }

    @GetMapping("/index")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String findAll(@RequestParam(name = "textSearch", required = false, defaultValue = "") String textSearch, Pageable pageable, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("employee",employeeService.findEmployeeByEmail(authentication.getName()));
        model.addAttribute("page", employeeService.findAll(textSearch, pageable));
        return "employee/index";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showAdd(Model model) {
        model.addAttribute("employee", new EmployeeDTO());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("departments", departmentService.findAll());
        return "employee/add";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String doAdd(@Valid @ModelAttribute("employee") EmployeeDTO employeeDTO, BindingResult bindingResult, Model model, @RequestParam("rePassword") String rePassword) throws MessagingException {
        if (employeeService.existsByEmail(employeeDTO.getEmail())) {
            FieldError error = new FieldError("employee", "email", "email existed!");
            bindingResult.addError(error);
        }
        if (!employeeDTO.getPassword().equals(rePassword)) {
            FieldError error = new FieldError("employee", "password", "re password not match!");
            bindingResult.addError(error);
        }
        if (employeeService.existsByPhone(employeeDTO.getPhone())) {
            FieldError error = new FieldError("employee", "phone", "phone existed!");
            bindingResult.addError(error);
        }
        if (employeeService.existsByEmployeeCode(employeeDTO.getEmployeeCode())) {
            FieldError error = new FieldError("employee", "employeeCode", "employeeCode existed!");
            bindingResult.addError(error);
        }
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public String delete(@PathVariable Long id) {
        employeeService.delete(id);
        return "redirect:/employee/index";
    }

    @GetMapping("/edit")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public String edit(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("employee", employeeService.findEmployeeByEmail(authentication.getName()));
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        return "employee/edit";
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public String doEdit(@RequestParam("img") MultipartFile file, @ModelAttribute("employee") EmployeeDTO employeeDTO, BindingResult bindingResult, Model model) throws IOException {
        EmployeeDTO employeeByEmail = employeeService.findEmployeeByEmail(employeeDTO.getEmail());
        EmployeeDTO employeeByPhone = employeeService.findEmployeeByEmail(employeeDTO.getPhone());
        EmployeeDTO employeeByEmployeeCode = employeeService.findEmployeeByEmail(employeeDTO.getEmployeeCode());
        if (employeeByEmail != null && employeeByEmail.getId() != employeeDTO.getId()) {
            FieldError error = new FieldError("employee", "email", "email existed!");
            bindingResult.addError(error);
        }
        if (employeeByPhone != null && employeeByPhone.getId() != employeeDTO.getId()) {
            FieldError error = new FieldError("employee", "phone", "phone existed!");
            bindingResult.addError(error);
        }
        if (employeeByEmployeeCode != null && employeeByEmployeeCode.getId() != employeeDTO.getId()) {
            FieldError error = new FieldError("employee", "employeeCode", "employeeCode existed!");
            bindingResult.addError(error);
        }
        if (bindingResult.hasErrors()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            model.addAttribute("employee", employeeService.findEmployeeByEmail(authentication.getName()));
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("employees", employeeService.findAll());
            return "employee/edit";
        }
        EmployeeDTO employeeUpdate = employeeService.findOne(employeeDTO.getId()).get();
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
        }else {
            employeeDTO.setAvatarUrl(employeeUpdate.getAvatarUrl());
        }
        employeeService.update(employeeDTO);
        return "redirect:/profile/index";
    }

    @GetMapping("/detail/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public String detail(@PathVariable Long id, Model model) {
        EmployeeDTO employeeDTO = employeeService.findOne(id).get();
        model.addAttribute("skills", skillService.findAllByEmployeeId(id));
        model.addAttribute("projects", employeeDTO.getProjects());
        model.addAttribute("experiences", experienceService.findAllByEmployeeId(id));
        model.addAttribute("certificates", certificateService.findAllByEmployeeId(id));
        model.addAttribute("employee", employeeDTO);
        return "employee/detail";
    }

    @GetMapping("/export-pdf/{id}")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<byte[]> exportProfileToPdf(@PathVariable Long id) {
        EmployeeDTO employeeDTO = employeeService.findOne(id).orElse(null);
        if (employeeDTO == null) {
            return ResponseEntity.notFound().build();
        }
        Context context = new Context();
        context.setVariable("employee", employeeDTO);
        String htmlContent = templateEngine.process("employee/detail", context);
        try {
            ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            ITextFontResolver fontResolver = renderer.getFontResolver();
            fontResolver.addFont("static/assets/fonts/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            renderer.layout();
            renderer.createPDF(pdfOutputStream);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "employee.pdf");
            return new ResponseEntity<>(pdfOutputStream.toByteArray(), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/search")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public String search(@RequestParam(name = "textSearch", required = false, defaultValue = "") String textSearch, Pageable pageable, Model model) {
        model.addAttribute("page", employeeService.findAll(textSearch, pageable));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeeDTO employeeLogin = employeeService.findEmployeeByEmail(authentication.getName());
        model.addAttribute("employee", employeeLogin);
        return "employee/search";
    }
}

