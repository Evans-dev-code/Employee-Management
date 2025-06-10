package com.example.employees.WorkHistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkHistoryRepository extends JpaRepository<WorkHistoryEntity, Long> {
    List<WorkHistoryEntity> findByEmployeeId(Long employeeId);
}
