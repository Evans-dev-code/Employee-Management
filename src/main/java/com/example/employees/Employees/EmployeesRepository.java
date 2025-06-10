package com.example.employees.Employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Long> {
    List<Employees> findByFirstName(String firstName);
    Employees findByEmailId(String emailId);
    List<Employees> findByLastNameContaining(String keyword);

    // New methods for filtering
    List<Employees> findByPosition_Department_Id(Long departmentId);
    List<Employees> findByPosition_Id(Long positionId);
}
