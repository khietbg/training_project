package com.example.trianing_project.controller;

import com.example.trianing_project.service.dto.CertificateDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/employee/certificate")
public class CertificateController {
    @GetMapping("/add")
    public ModelAndView showAdd(){
        ModelAndView modelAndView = new ModelAndView("certificate/add");
        modelAndView.addObject("certificate",new CertificateDTO());
        return modelAndView;
    }
}
