package com.example.trianing_project.controller;

import com.example.trianing_project.repository.SkillRepository;
import com.example.trianing_project.service.SkillService;
import com.example.trianing_project.service.dto.SkillDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/skill")
public class SkillController {
    private final SkillService skillService;
    private final SkillRepository skillRepository;

    public SkillController(SkillService skillService, SkillRepository skillRepository) {
        this.skillService = skillService;
        this.skillRepository = skillRepository;
    }

    @PostMapping("/add")
    public String doAdd(@ModelAttribute("skill") SkillDTO skillDto) {
        skillService.save(skillDto);
        return "redirect:/skill/add";
    }

    @GetMapping("/add")
    public String showAdd(Model model) {
        model.addAttribute("skill", new SkillDTO());
        return "/skill/add";
    }

    @PostMapping("/edit")
    public String doEdit(@ModelAttribute("skill") SkillDTO skillDto) {
        skillService.save(skillDto);
        return "redirect:/skill/edit";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("skill", skillService.findOne(id).get());
        return "/skill/edit";
    }

    @GetMapping("/delete/{id}")
    public String doDelete(@PathVariable("id") Long id) {
        if (!skillRepository.existsById(id)) {
            return "redirect:/skill/index";
        }
        skillService.delete(id);
        return "redirect:/skill/index";
    }
}