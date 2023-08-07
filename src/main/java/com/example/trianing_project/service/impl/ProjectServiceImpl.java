package com.example.trianing_project.service.impl;

import com.example.trianing_project.domain.Employee;
import com.example.trianing_project.domain.Project;
import com.example.trianing_project.repository.EmployeeRepository;
import com.example.trianing_project.repository.ProjectRepository;
import com.example.trianing_project.service.ProjectService;
import com.example.trianing_project.service.dto.EmployeeDTO;
import com.example.trianing_project.service.dto.ProjectDTO;
import com.example.trianing_project.service.mapper.EmployeeMapper;
import com.example.trianing_project.service.mapper.ProjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final EmployeeMapper employeeMapper;

    public ProjectServiceImpl(EmployeeRepository employeeRepository, ProjectRepository projectRepository, ProjectMapper projectMapper, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<ProjectDTO> findAll() {
        return projectMapper.toDto(projectRepository.findAll());
    }

    @Override
    public ProjectDTO save(ProjectDTO projectDto) {
        Project project = projectMapper.toEntity(projectDto);
        projectRepository.save(project);

        return projectDto;
    }

    @Override
    public Page<ProjectDTO> findAll(String textSearch, Pageable pageable) {
        return projectRepository.findAllByNameContainingIgnoreCase(textSearch, pageable).map(projectMapper::toDto);
    }

    @Override
    public Optional<ProjectDTO> findOne(Long id) {
        return projectRepository.findById(id).map(projectMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
