package com.example.trianing_project.service;
import com.example.trianing_project.service.dto.EmployeeDTO;


public interface EmployeeService extends GenericService<EmployeeDTO, Long> {
    EmployeeDTO findEmployeeByEmail(String usernameOrEmail);
    EmployeeDTO updatePassword(EmployeeDTO employeeDTO);
}
