package com.example.trianing_project.service.mapper.impl;

import com.example.trianing_project.domain.Employee;
import com.example.trianing_project.domain.Project;
import com.example.trianing_project.service.dto.ProjectDto;
import com.example.trianing_project.service.mapper.ProjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectMapperImpl implements ProjectMapper {
    @Override
    public Project toEntity(ProjectDto dto) {
        if (dto == null) {
            return null;
        }
        Project project = new Project();
        project.setId(dto.getId());
        project.setName(dto.getName());
        project.setOs(dto.getOs());
        project.setFramework(dto.getFramework());
        project.setLanguage(dto.getLanguage());
        project.setStartDate(dto.getStartDate());
        project.setEndDate(dto.getEndDate());
        project.setPmId(dto.getPmId());
        return project;
    }

    @Override
    public ProjectDto toDto(Project entity) {
        if (entity == null) {
            return null;
        }
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(entity.getId());
        projectDto.setName(entity.getName());
        projectDto.setOs(entity.getOs());
        projectDto.setFramework(entity.getFramework());
        projectDto.setLanguage(entity.getLanguage());
        projectDto.setStartDate(entity.getStartDate());
        projectDto.setEndDate(entity.getEndDate());
        projectDto.setPmId(entity.getPmId());

        Employee employee = entity.getPm();
        if (employee != null) {
            projectDto.setPmName(employee.getFirstName() + " " + employee.getLastName());
        }
        return projectDto;
    }

    @Override
    public List<Project> toEntity(List<ProjectDto> dtoList) {
        if (dtoList == null) {
            return null;
        }
        List<Project> list = new ArrayList<>(dtoList.size());
        for (ProjectDto projectDto : dtoList) {
            list.add(toEntity(projectDto));
        }
        return list;
    }

    @Override
    public List<ProjectDto> toDto(List<Project> entityList) {
        if (entityList == null) {
            return null;
        }
        List<ProjectDto> list = new ArrayList<>(entityList.size());
        for (Project project : entityList) {
            list.add(toDto(project));
        }
        return list;
    }
}
