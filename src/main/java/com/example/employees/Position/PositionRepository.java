package com.example.employees.Position;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepository extends JpaRepository<PositionEntity, Long> {
    List<PositionEntity> findByDepartmentId(Long departmentId);

}
