package com.example.trianing_project.repository;

import com.example.trianing_project.domain.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>, JpaSpecificationExecutor<Department> {
    List<Department> findAll();
    Page<Department> findAllByNameIsContainingIgnoreCase(String name,Pageable pageable);
    boolean existsByParentId(Long id);
}
