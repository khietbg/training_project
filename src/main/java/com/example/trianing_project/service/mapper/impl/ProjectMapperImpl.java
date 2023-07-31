package com.example.trianing_project.service.mapper.Impl;

import com.example.trianing_project.domain.Employee;
import com.example.trianing_project.domain.Project;
import com.example.trianing_project.service.dto.ProjectDTO;
import com.example.trianing_project.service.mapper.ProjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectMapperImpl implements ProjectMapper {
    @Override
    public Project toEntity(ProjectDTO dto) {
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
    public ProjectDTO toDto(Project entity) {
        if (entity == null) {
            return null;
        }
        ProjectDTO projectDto = new ProjectDTO();
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
            projectDto.setPmName(employee.getFirstName() + " " + employee.getLast_name());
        }
        return projectDto;
    }

    @Override
    public List<Project> toEntity(List<ProjectDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }
        List<Project> list = new ArrayList<>(dtoList.size());
        for (ProjectDTO projectDto : dtoList) {
            list.add(toEntity(projectDto));
        }
        return list;
    }

    @Override
    public List<ProjectDTO> toDto(List<Project> entityList) {
        if (entityList == null) {
            return null;
        }
        List<ProjectDTO> list = new ArrayList<>(entityList.size());
        for (Project project : entityList) {
            list.add(toDto(project));
        }
        return list;
    }
}
