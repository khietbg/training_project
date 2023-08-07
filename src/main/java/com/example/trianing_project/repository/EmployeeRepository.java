package com.example.trianing_project.repository;

import com.example.trianing_project.domain.Employee;
import com.example.trianing_project.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findEmployeeByEmail(String usernameOrEmail);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByEmployeeCode(String employeeCode);

    Employee findEmployeeByPhone(String phone);

    Employee findEmployeeByEmployeeCode(String employeeCode);

    Page<Employee> findAllByFirstNameContainingIgnoreCase(String search, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT  * from employee as e join employee_project ep on e.id = ep.employ_id  where ep.project_id=:proId")
    List<Employee> findByProjectId(@Param("proId") Long projectId);
}
