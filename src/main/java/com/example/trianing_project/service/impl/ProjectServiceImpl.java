package com.example.trianing_project.service.impl;

import com.example.trianing_project.domain.Project;
import com.example.trianing_project.repository.ProjectRepository;
import com.example.trianing_project.service.ProjectService;
import com.example.trianing_project.service.dto.ProjectDto;
import com.example.trianing_project.service.mapper.ProjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public ProjectDto save(ProjectDto projectDto) {
        Project project = projectMapper.toEntity(projectDto);
        project = projectRepository.save(project);
        return projectMapper.toDto(project);
    }

    @Override
    public Page<ProjectDto> findAll(String textSearch, Pageable pageable) {
        return projectRepository.findAllByNameContainingIgnoreCase(textSearch,pageable).map(projectMapper::toDto);
    }

    @Override
    public Optional<ProjectDto> findOne(Long id) {
        return projectRepository.findById(id).map(projectMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

}
