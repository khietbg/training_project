package com.example.trianing_project.service;

import com.example.trianing_project.domain.Skill;
import com.example.trianing_project.service.dto.ProjectDto;
import com.example.trianing_project.service.dto.SkillDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    SkillDto save(SkillDto skillDto);

    List<SkillDto> findAllByEmployeeId(Long employeeId);

    Optional<SkillDto> findOne(Long id);

    void delete(Long id);
}
