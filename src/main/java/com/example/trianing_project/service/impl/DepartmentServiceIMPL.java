package com.example.trianing_project.service.impl;

import com.example.trianing_project.domain.Department;
import com.example.trianing_project.repository.DepartmentRepository;
import com.example.trianing_project.service.DepartmentService;
import com.example.trianing_project.service.dto.DepartmentDTO;
import com.example.trianing_project.service.mapper.DepartmentMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceIMPL implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentServiceIMPL(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<DepartmentDTO> findAll() {
        return departmentMapper.toDto(departmentRepository.findAll());
    }

    @Override
    public Page<DepartmentDTO> findAllByName(String searchName, Pageable pageable) {
        return departmentRepository.findAllByNameIsContainingIgnoreCase(searchName,pageable).map( departmentMapper::toDto);
    }

    @Override
    public Optional<DepartmentDTO> findOne(Long id) {
        return departmentRepository.findById(id).map(departmentMapper::toDto);
    }

    @Override
    public DepartmentDTO save(DepartmentDTO departmentDTO) {
        Department department = departmentMapper.toEntity(departmentDTO);
        departmentRepository.save(department);
        return departmentMapper.toDto(department);
    }

    @Override
    public void delete(Long id) {
            departmentRepository.deleteById(id);
    }

    @Override
    public boolean existsByParentId(Long id) {
        return departmentRepository.existsByParentId(id);
    }
}
