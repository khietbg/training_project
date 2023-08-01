package com.example.trianing_project.service.mapper.impl;

import com.example.trianing_project.domain.Department;
import com.example.trianing_project.service.dto.DepartmentDTO;
import com.example.trianing_project.service.mapper.DepartmentMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public Department toEntity(DepartmentDTO dto) {
        if (dto == null) {
            return null;
        }
        Department department = new Department();
        department.setId(dto.getId());
        department.setName(dto.getName());
        department.setDepartmentCode(dto.getDepartmentCode());
        department.setDescription(dto.getDescription());
        department.setParentId(dto.getParentId());
        return department;
    }

    @Override
    public DepartmentDTO toDto(Department entity) {
        if(entity == null) {
            return null;
        }
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(entity.getId());
        departmentDTO.setDepartmentCode(entity.getDepartmentCode());
        departmentDTO.setName(entity.getName());
        departmentDTO.setDescription(entity.getDescription());
        Department department = entity.getDepartment();
        if (department != null) {
            departmentDTO.setParentName(department.getName());
        }
        return departmentDTO;
    }

    @Override
    public List<Department> toEntity(List<DepartmentDTO> dtoList) {
        if ( dtoList == null) {
            return null;
        }
        List<Department> departments =new ArrayList<>();
        for (DepartmentDTO departmentDTO:dtoList) {
            departments.add( toEntity(departmentDTO));
        }
        return departments;
    }

    @Override
    public List<DepartmentDTO> toDto(List<Department> entityList) {
        if(entityList == null) {
            return null;
        }
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        for (Department department:entityList) {
            departmentDTOS.add(toDto(department));
        }
        return departmentDTOS;
    }
}
