package com.example.trianing_project.repository;

import com.example.trianing_project.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findEmployeeByEmail(String usernameOrEmail);

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByEmployeeCode(String employeeCode);
    Employee findEmployeeByPhone(String phone);
    Employee findEmployeeByEmployeeCode(String employeeCode);

    Page<Employee> findAllByFirstNameContainingIgnoreCase(String search, Pageable pageable);
}
