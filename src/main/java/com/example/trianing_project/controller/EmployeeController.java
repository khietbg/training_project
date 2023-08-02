package com.example.trianing_project.controller;


import com.example.trianing_project.repository.EmployeeRepository;
import com.example.trianing_project.service.DepartmentService;
import com.example.trianing_project.service.EmployeeService;
import com.example.trianing_project.service.dto.EmployeeDTO;
import com.example.trianing_project.service.email.SendEmailService;
import com.lowagie.text.pdf.BaseFont;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;
    private final SendEmailService mailService;
    private final DepartmentService departmentService;
    private final TemplateEngine templateEngine;

    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository, SendEmailService mailService, DepartmentService departmentService, TemplateEngine templateEngine) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
        this.mailService = mailService;
        this.departmentService = departmentService;
        this.templateEngine = templateEngine;
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
    public String doAdd(@Valid @ModelAttribute("department") EmployeeDTO employeeDTO, BindingResult bindingResult, Model model) throws MessagingException, MessagingException {
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
        model.addAttribute("employee", employeeService.findOne(id).get());
        return "employee/detail";
    }

    @GetMapping("/back")
    public String back() {
        return "redirect:/employee/index";
    }

    @GetMapping("/export-pdf/{id}")
    @ResponseBody
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
}
