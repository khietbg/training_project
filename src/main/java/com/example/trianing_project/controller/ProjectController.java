package com.example.trianing_project.controller;

import com.example.trianing_project.repository.ProjectRepository;
import com.example.trianing_project.service.EmployeeService;
import com.example.trianing_project.service.ProjectService;
import com.example.trianing_project.service.dto.EmployeeDTO;
import com.example.trianing_project.service.dto.ProjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/project")
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
public class ProjectController {

}
