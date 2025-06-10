package com.example.employees.EmergencyContact;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContactEntity, Long> {
    List<EmergencyContactEntity> findByEmployeeId(Long employeeId);
}
