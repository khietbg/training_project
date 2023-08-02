package com.example.trianing_project.service.mapper.impl;

import com.example.trianing_project.domain.Employee;
import com.example.trianing_project.domain.Project;
import com.example.trianing_project.domain.Role;
import com.example.trianing_project.service.dto.EmployeeDTO;
import com.example.trianing_project.service.mapper.EmployeeMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {
    @Override
    public Employee toEntity(EmployeeDTO dto) {
        if (dto == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setPassword(dto.getPassword());
        employee.setEmail(dto.getEmail());
        employee.setAvatarUrl(dto.getAvatarUrl());
        employee.setAddress(dto.getAddress());
        employee.setPhone(dto.getPhone());
        employee.setBirthDate(dto.getBirthDate());
        employee.setCitizenCode(employee.getCitizenCode());
        employee.setCoefficientsSalary(dto.getCoefficientsSalary());
        employee.setLicenseDate(dto.getLicenseDate());
        employee.setLicensePlace(dto.getLicensePlace());
        employee.setDepartmentId(dto.getDepartmentId());
        employee.setLevel(dto.getLevel());
        employee.setManagerId(dto.getManagerId());
        employee.setPosition(dto.getPosition());
        employee.setSex(dto.isSex());
        employee.setStartDate(dto.getStartDate());
        employee.setEmployeeCode(dto.getEmployeeCode());
        return employee;
    }

    @Override
    public EmployeeDTO toDto(Employee entity) {
        if (entity == null) {
            return null;
        }
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(entity.getId());
        employeeDTO.setEmail(entity.getEmail());
        employeeDTO.setAddress(entity.getAddress());
        employeeDTO.setPassword(entity.getPassword());
        employeeDTO.setAvatarUrl(entity.getAvatarUrl());
        employeeDTO.setCitizenCode(entity.getCitizenCode());
        employeeDTO.setBirthDate(entity.getBirthDate());
        employeeDTO.setEmployeeCode(employeeDTO.getEmployeeCode());
        employeeDTO.setCoefficientsSalary(entity.getCoefficientsSalary());
        employeeDTO.setLevel(entity.getLevel());
        employeeDTO.setDepartmentId(employeeDTO.getDepartmentId());
        employeeDTO.setFirstName(entity.getFirstName());
        employeeDTO.setLastName(entity.getLastName());
        employeeDTO.setLicenseDate(entity.getLicenseDate());
        employeeDTO.setLicensePlace(entity.getLicensePlace());
        employeeDTO.setManagerId(entity.getManagerId());
        employeeDTO.setEmployeeCode(entity.getEmployeeCode());
        employeeDTO.setPhone(entity.getPhone());
        employeeDTO.setStartDate(entity.getStartDate());
        employeeDTO.setPosition(entity.getPosition());
        employeeDTO.setDepartmentId(entity.getDepartmentId());
        if (entity.getManager() != null) {
            employeeDTO.setManagerName(entity.getManager().getFirstName());
        }
        if (entity.getDepartment() != null) {
            employeeDTO.setDepartmentName(entity.getDepartment().getName());
        }
        Set<Role> roles = entity.getRoles();
        if (roles != null) {
            employeeDTO.setRoles(roles.stream().map(Role::getRoleName).collect(Collectors.toSet()));
        }
        Set<Project> projects = entity.getProjects();
        if (projects != null) {
            employeeDTO.setProjects(projects.stream().map(Project::getName).collect(Collectors.toSet()));
        }
        return employeeDTO;
    }

    @Override
    public List<Employee> toEntity(List<EmployeeDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }
        List<Employee> employees = new ArrayList<>();
        for (EmployeeDTO dto : dtoList) {
            employees.add(toEntity(dto));
        }
        return employees;
    }

    @Override
    public List<EmployeeDTO> toDto(List<Employee> entityList) {
        if (entityList == null) {
            return null;
        }
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee e : entityList) {
            employeeDTOS.add(toDto(e));
        }
        return employeeDTOS;
    }
}
