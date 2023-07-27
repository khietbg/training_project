package com.example.trianing_project.service.serviceImpl;


import com.example.trianing_project.domain.Skill;
import com.example.trianing_project.repository.SkillRepository;
import com.example.trianing_project.service.SkillService;
import com.example.trianing_project.service.dto.SkillDto;
import com.example.trianing_project.service.mapper.SkillMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    public SkillServiceImpl(SkillRepository skillRepository, SkillMapper skillMapper) {
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
    }

    @Override
    public SkillDto save(SkillDto skillDto) {
        Skill skill = skillMapper.toEntity(skillDto);
        skill = skillRepository.save(skill);
        return skillMapper.toDto(skill);
    }

    @Override
    public List<SkillDto> findAllByEmployeeId(Long employeeId) {
        return skillMapper.toDto(skillRepository.findAllByEmployeeId(employeeId));
    }


    @Override
    public Optional<SkillDto> findOne(Long id) {
        return skillRepository.findById(id).map(skillMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        skillRepository.deleteById(id);
    }
}
