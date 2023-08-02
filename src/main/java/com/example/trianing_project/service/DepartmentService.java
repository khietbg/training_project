package com.example.trianing_project.service;

import com.example.trianing_project.domain.Department;
import com.example.trianing_project.service.dto.DepartmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<DepartmentDTO> findAll();
    Page<DepartmentDTO> findAllByName(String searchName, Pageable pageable);
    Optional<DepartmentDTO> findOne(Long id);
    DepartmentDTO save(DepartmentDTO departmentDTO);
    void delete(Long id);
    boolean existsByParentId(Long id);
    Optional<DepartmentDTO> findByDepartmentCode(String departmentCode);
}
