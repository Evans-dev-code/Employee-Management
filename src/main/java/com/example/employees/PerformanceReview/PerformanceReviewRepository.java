package com.example.employees.PerformanceReview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceReviewRepository extends JpaRepository<PerformanceReviewEntity, Long> {
    List<PerformanceReviewEntity> findByEmployeeId(Long employeeId);
}
