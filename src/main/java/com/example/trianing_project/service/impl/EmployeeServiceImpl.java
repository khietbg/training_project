package com.example.trianing_project.service.impl;

import com.example.trianing_project.domain.Employee;
import com.example.trianing_project.repository.EmployeeRepository;
import com.example.trianing_project.service.EmployeeService;
import com.example.trianing_project.service.dto.EmployeeDTO;
import com.example.trianing_project.service.mapper.EmployeeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeMapper.toDto(employeeRepository.findAll());
    }

    @Override
    public Page<EmployeeDTO> findAll(String search, Pageable pageable) {
        return employeeRepository.findAllByFirstNameOrlOrLastNameContainingIgnoreCase(search, pageable).map(employeeMapper::toDto);
    }

    @Override
    public Optional<EmployeeDTO> findOne(Long id) {
        return employeeRepository.findById(id).map(employeeMapper::toDto);
    }

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEntity(employeeDTO);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

}
