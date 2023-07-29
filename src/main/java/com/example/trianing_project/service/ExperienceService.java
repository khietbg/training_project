package com.example.trianing_project.service;

import com.example.trianing_project.domain.Employee;
import com.example.trianing_project.domain.Experience;
import com.example.trianing_project.service.dto.EmployeeDTO;
import com.example.trianing_project.service.dto.ExperienceDTO;

import java.util.List;

public interface ExperienceService extends GenericService<ExperienceDTO, Long> {
    List<ExperienceDTO> findAllByEmployee(EmployeeDTO employeeDTO);
}
