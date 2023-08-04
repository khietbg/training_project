package com.example.trianing_project.service.impl;

import com.example.trianing_project.repository.ExperienceRepository;
import com.example.trianing_project.service.ExperienceService;
import com.example.trianing_project.service.dto.EmployeeDTO;
import com.example.trianing_project.service.dto.ExperienceDTO;
import com.example.trianing_project.service.mapper.EmployeeMapper;
import com.example.trianing_project.service.mapper.ExperienceMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceServiceImpl implements ExperienceService {
    private final ExperienceRepository experienceRepository;
    private final ExperienceMapper experienceMapper;
    private final EmployeeMapper employeeMapper;

    public ExperienceServiceImpl(ExperienceRepository experienceRepository, ExperienceMapper experienceMapper, EmployeeMapper employeeMapper) {
        this.experienceRepository = experienceRepository;
        this.experienceMapper = experienceMapper;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<ExperienceDTO> findAll() {
        return experienceMapper.toDto(experienceRepository.findAll());
    }

    @Override
    public Page<ExperienceDTO> findAll(String search, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ExperienceDTO> findOne(Long id) {
        return experienceRepository.findById(id).map(experienceMapper::toDto);
    }

    @Override
    public ExperienceDTO save(ExperienceDTO experienceDTO) {
        return experienceMapper.toDto(experienceRepository.save(experienceMapper.toEntity(experienceDTO)));
    }

    @Override
    public void delete(Long id) {
        experienceRepository.deleteById(id);
    }

    @Override
    public List<ExperienceDTO> findAllByEmployeeId(Long id) {
        return experienceMapper.toDto(experienceRepository.findAllByEmployeeId(id));
    }
}
