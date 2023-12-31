package com.example.trianing_project.service.impl;

import com.example.trianing_project.domain.Employee;
import com.example.trianing_project.domain.Role;
import com.example.trianing_project.repository.DepartmentRepository;
import com.example.trianing_project.repository.EmployeeRepository;
import com.example.trianing_project.service.EmployeeService;
import com.example.trianing_project.service.RoleService;
import com.example.trianing_project.service.dto.EmployeeDTO;
import com.example.trianing_project.service.mapper.EmployeeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final DepartmentRepository departmentRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, DepartmentRepository departmentRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.departmentRepository = departmentRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
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
        Employee employee = employeeMapper.toEntity(employeeDTO);
        Set<Role> roles = new HashSet<>();
        if (employeeDTO.getRoles() == null || employeeDTO.getRoles().isEmpty()) {
            Role role = roleService.findByRoleName("USER");
            roles.add(role);
        } else {
            employeeDTO.getRoles().forEach(role -> {
                switch (role) {
                    case "ADMIN":
                        Role adminRole = roleService.findByRoleName("ADMIN");
                        roles.add(adminRole);
                    case "USER":
                        Role userRole = roleService.findByRoleName("USER");
                        roles.add(userRole);
                }
            });
        }
        employee.setRoles(roles);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
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

    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEntity(employeeDTO);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    @Override
    public boolean existsByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return employeeRepository.existsByPhone(phone);
    }

    @Override
    public boolean existsByEmployeeCode(String employeeCode) {
        return employeeRepository.existsByEmployeeCode(employeeCode);
    }

    @Override
    public EmployeeDTO findEmployeeByPhone(String phone) {
        return employeeMapper.toDto(employeeRepository.findEmployeeByPhone(phone));
    }

    @Override
    public EmployeeDTO findEmployeeByEmployeeCode(String employeeCode) {
        return employeeMapper.toDto(employeeRepository.findEmployeeByEmployeeCode(employeeCode));
    }

    @Override
    public List<Employee> findByProjectId(Long id) {
        return employeeRepository.findByProjectId(id);
    }
}
