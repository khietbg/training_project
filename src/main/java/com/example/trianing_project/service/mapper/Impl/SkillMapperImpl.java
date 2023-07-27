package com.example.trianing_project.service.mapper.Impl;

import com.example.trianing_project.domain.Employee;
import com.example.trianing_project.domain.Skill;
import com.example.trianing_project.service.dto.SkillDto;
import com.example.trianing_project.service.mapper.SkillMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SkillMapperImpl implements SkillMapper {
    @Override
    public Skill toEntity(SkillDto dto) {
        if (dto == null) {
            return null;
        }
        Skill skill = new Skill();
        skill.setId(dto.getId());
        skill.setName(dto.getName());
        skill.setLevel(dto.getLevel());
        skill.setDescription(dto.getDescription());
        skill.setMonth(dto.getMonth());
        skill.setYear(dto.getYear());
        skill.setEmployeeId(dto.getEmployeeId());
        return skill;
    }

    @Override
    public SkillDto toDto(Skill entity) {
        if (entity == null) {
            return null;
        }
        SkillDto skillDto = new SkillDto();
        skillDto.setId(entity.getId());
        skillDto.setName(entity.getName());
        skillDto.setLevel(entity.getLevel());
        skillDto.setDescription(entity.getDescription());
        skillDto.setMonth(entity.getMonth());
        skillDto.setYear(entity.getYear());
        skillDto.setEmployeeId(entity.getEmployeeId());
        Employee employee = entity.getEmployee();
        if (employee != null) {
            skillDto.setEmployeeName(employee.getFirstName() + " " + employee.getLastName());
        }

        return skillDto;
    }

    @Override
    public List<Skill> toEntity(List<SkillDto> dtoList) {
        if (dtoList == null) {
            return null;
        }
        List<Skill> list = new ArrayList<>(dtoList.size());
        for (SkillDto skillDto : dtoList) {
            list.add(toEntity(skillDto));
        }
        return list;
    }

    @Override
    public List<SkillDto> toDto(List<Skill> entityList) {
        if (entityList == null) {
            return null;
        }
        List<SkillDto> list = new ArrayList<>(entityList.size());
        for (Skill skill : entityList) {
            list.add(toDto(skill));
        }
        return list;
    }
}
