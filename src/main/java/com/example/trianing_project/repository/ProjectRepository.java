package com.example.trianing_project.repository;

import com.example.trianing_project.domain.Employee;
import com.example.trianing_project.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findAllByNameContainingIgnoreCase(String textSearchName, Pageable pageable);
}
