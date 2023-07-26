package com.example.trianing_project.controller;

import com.example.trianing_project.repository.SkillRepository;
import com.example.trianing_project.service.SkillService;
import com.example.trianing_project.service.dto.SkillDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


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
    public ModelAndView doAdd(@ModelAttribute("skill") SkillDto skillDto) {
        skillService.save(skillDto);
        ModelAndView modelAndView = new ModelAndView("redirect:/skill/add");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView showAdd() {
        ModelAndView modelAndView = new ModelAndView("/skill/add");
        modelAndView.addObject("skill", new SkillDto());
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView doEdit(@ModelAttribute("skill") SkillDto skillDto) {
        skillService.save(skillDto);
        ModelAndView modelAndView = new ModelAndView("redirect:/skill/index");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/skill/edit");
        modelAndView.addObject("skills", skillService.findOne(id).get());
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView showDetail(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/skill/detail");
        modelAndView.addObject("department", skillService.findOne(id).get());
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView doDelete(@PathVariable("id") Long id) {
        if (!skillRepository.existsById(id)) {
            return new ModelAndView("/skill/index");
        }
        skillService.delete(id);
        return new ModelAndView("redirect:/skill/index");
    }
}
