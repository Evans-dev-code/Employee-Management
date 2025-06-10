package com.example.employees.Education;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<EducationEntity, Long> {
    List<EducationEntity> findByEmployeeId(Long employeeId);
}

