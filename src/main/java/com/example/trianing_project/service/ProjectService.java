package com.example.trianing_project.service;

import com.example.trianing_project.service.dto.ProjectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface ProjectService {
    ProjectDto save(ProjectDto projectDto);

    Page<ProjectDto> findAll(String textSearch, Pageable pageable);

    Optional<ProjectDto> findOne(Long id);

    void delete(Long id);

}
