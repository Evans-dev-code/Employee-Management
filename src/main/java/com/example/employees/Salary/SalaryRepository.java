package com.example.employees.Salary;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SalaryRepository extends JpaRepository<SalaryEntity, Long> {
    List<SalaryEntity> findByEmployeeIdOrderByEffectiveDateDesc(Long employeeId);
}
