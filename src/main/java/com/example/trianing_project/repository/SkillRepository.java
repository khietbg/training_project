package com.example.trianing_project.repository;

import com.example.trianing_project.domain.Skill;
import com.example.trianing_project.service.dto.GetData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findAllByEmployeeId(Long employeeId);
    @Query(nativeQuery = true,value = "select  s.name as name, count(s.name) as data from skill as s group by s.name order by data DESC limit 10;")
    List<GetData> getDataByName();
}
