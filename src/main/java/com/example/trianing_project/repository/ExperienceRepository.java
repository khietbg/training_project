package com.example.trianing_project.repository;

import com.example.trianing_project.domain.Employee;
import com.example.trianing_project.domain.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findAllByEmployeeId(Long id);
}
