package com.example.trianing_project.service.impl;


import com.example.trianing_project.domain.Skill;
import com.example.trianing_project.repository.SkillRepository;
import com.example.trianing_project.service.SkillService;
import com.example.trianing_project.service.dto.SkillDTO;
import com.example.trianing_project.service.mapper.SkillMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class SkillServiceIMPL implements SkillService {
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    public SkillServiceIMPL(SkillRepository skillRepository, SkillMapper skillMapper) {
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
    }

    @Override
    public SkillDTO save(SkillDTO skillDto) {
        Skill skill = skillMapper.toEntity(skillDto);
        skill = skillRepository.save(skill);
        return skillMapper.toDto(skill);
    }

    @Override
    public List<SkillDTO> findAllByEmployeeId(Long employeeId) {
        return skillMapper.toDto(skillRepository.findAllByEmployeeId(employeeId));
    }


    @Override
    public Optional<SkillDTO> findOne(Long id) {
        return skillRepository.findById(id).map(skillMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        skillRepository.deleteById(id);
    }
}
