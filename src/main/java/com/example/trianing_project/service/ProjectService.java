package com.example.trianing_project.service;


import com.example.trianing_project.service.dto.ProjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    ProjectDTO save(ProjectDTO projectDto);
    List<ProjectDTO> findAll();

    Page<ProjectDTO> findAll(String textSearch, Pageable pageable);

    Optional<ProjectDTO> findOne(Long id);

    void delete(Long id);

}
