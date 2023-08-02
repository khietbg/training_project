package com.example.trianing_project.service;

import com.example.trianing_project.service.dto.EmployeeDTO;
import com.example.trianing_project.service.dto.ExperienceDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExperienceService extends GenericService<ExperienceDTO, Long> {
    List<ExperienceDTO> findAllByEmployee(EmployeeDTO employeeDTO);
}
