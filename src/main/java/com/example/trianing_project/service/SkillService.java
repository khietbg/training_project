package com.example.trianing_project.service;

import com.example.trianing_project.service.dto.SkillDTO;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    SkillDTO save(SkillDTO skillDto);

    List<SkillDTO> findAllByEmployeeId(Long employeeId);

    Optional<SkillDTO> findOne(Long id);

    void delete(Long id);
}
