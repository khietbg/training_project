package com.example.trianing_project.service;
import com.example.trianing_project.domain.Employee;
import com.example.trianing_project.service.dto.EmployeeDTO;


public interface EmployeeService extends GenericService<EmployeeDTO, Long> {
    EmployeeDTO findEmployeeByEmail(String usernameOrEmail);
    EmployeeDTO update(EmployeeDTO employeeDTO);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByEmployeeCode(String employeeCode);
    EmployeeDTO findEmployeeByPhone(String phone);
    EmployeeDTO findEmployeeByEmployeeCode(String employeeCode);
}
