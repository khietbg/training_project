package com.example.trianing_project.service.mapper.impl;

import com.example.trianing_project.domain.Experience;
import com.example.trianing_project.service.dto.ExperienceDTO;
import com.example.trianing_project.service.mapper.ExperienceMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExperienceMapperImpl implements ExperienceMapper {
    @Override
    public Experience toEntity(ExperienceDTO dto) {
        if (dto == null) {
            return null;
        }
        Experience experience = new Experience();
        experience.setId(dto.getId());
        experience.setLanguage(dto.getLanguage());
        experience.setOs(dto.getOs());
        experience.setFramework(dto.getFramework());
        experience.setDescription(dto.getDescription());
        experience.setLink(dto.getLink());
        experience.setEmployeeId(dto.getEmployeeId());
        experience.setPosition(dto.getPosition());
        experience.setTeamSize(dto.getTeamSize());
        experience.setTimeStart(dto.getTimeStart());
        experience.setTimeEnd(dto.getTimeEnd());
        experience.setWorkPlace(dto.getWorkPlace());
        return experience;
    }

    @Override
    public ExperienceDTO toDto(Experience entity) {
        if (entity == null) {
            return null;
        }
        ExperienceDTO experienceDTO = new ExperienceDTO();
        experienceDTO.setId(entity.getId());
        experienceDTO.setLanguage(entity.getLanguage());
        experienceDTO.setOs(entity.getOs());
        experienceDTO.setFramework(entity.getFramework());
        experienceDTO.setDescription(entity.getDescription());
        experienceDTO.setLink(entity.getLink());
        experienceDTO.setEmployeeId(entity.getEmployeeId());
        experienceDTO.setPosition(entity.getPosition());
        experienceDTO.setTeamSize(entity.getTeamSize());
        experienceDTO.setTimeStart(entity.getTimeStart());
        experienceDTO.setTimeEnd(entity.getTimeEnd());
        experienceDTO.setWorkPlace(entity.getWorkPlace());
        return experienceDTO;
    }

    @Override
    public List<Experience> toEntity(List<ExperienceDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }
        List<Experience> experiences = new ArrayList<>();
        for (ExperienceDTO dto : dtoList) {
            experiences.add(toEntity(dto));
        }

        return experiences;
    }

    @Override
    public List<ExperienceDTO> toDto(List<Experience> entityList) {
        if (entityList == null) {
            return null;
        }
        List<ExperienceDTO> experiences = new ArrayList<>();
        for (Experience entity : entityList) {
            experiences.add(toDto(entity));
        }
        return experiences;
    }
}
