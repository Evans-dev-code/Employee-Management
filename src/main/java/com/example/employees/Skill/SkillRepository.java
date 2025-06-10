package com.example.employees.Skill;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<SkillEntity, Long> {
    List<SkillEntity> findByEmployeeId(Long employeeId);
}
