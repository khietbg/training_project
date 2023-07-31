package com.example.trianing_project.service.impl;

import com.example.trianing_project.domain.Employee;
import com.example.trianing_project.repository.DepartmentRepository;
import com.example.trianing_project.repository.EmployeeRepository;
import com.example.trianing_project.service.DepartmentService;
import com.example.trianing_project.service.EmployeeService;
import com.example.trianing_project.service.dto.EmployeeDTO;
import com.example.trianing_project.service.mapper.EmployeeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeMapper.toDto(employeeRepository.findAll());
    }

    @Override
    public Page<EmployeeDTO> findAll(String search, Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAllByFirstNameContainingIgnoreCase(search, pageable);
        Page<EmployeeDTO> dtos = employees.map(employeeMapper::toDto);
        return dtos;
    }

    @Override
    public Optional<EmployeeDTO> findOne(Long id) {
        return employeeRepository.findById(id).map(employeeMapper::toDto);
    }

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        if (employeeDTO.getId() != null){
            Employee employee = employeeRepository.findById(employeeDTO.getId()).get();
            employee.setPosition(employeeDTO.getPosition());
            employee.setCoefficientsSalary(employeeDTO.getCoefficientsSalary());
            employee.setDepartmentId(employeeDTO.getDepartmentId());
            employee.setDepartment(departmentRepository.findById(employeeDTO.getDepartmentId()).get());
            return employeeMapper.toDto(employeeRepository.save(employee));
        }
        Employee employee = employeeMapper.toEntity(employeeDTO);
        employee.setStartDate(LocalDate.now());
        employee.setManager(employeeRepository.findById(employeeDTO.getDepartmentId()).get());
        employee.setDepartment(departmentRepository.findById(employeeDTO.getDepartmentId()).get());
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDTO findEmployeeByEmail(String usernameOrEmail) {
        return employeeMapper.toDto(employeeRepository.findEmployeeByEmail(usernameOrEmail));
    }
}
