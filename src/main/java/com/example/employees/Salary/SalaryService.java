package com.example.employees.Salary;

import com.example.employees.Employees.Employees;
import com.example.employees.Employees.EmployeesRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalaryService {

    private final SalaryRepository salaryRepository;
    private final EmployeesRepository employeesRepository;

    public SalaryService(SalaryRepository salaryRepository, EmployeesRepository employeesRepository) {
        this.salaryRepository = salaryRepository;
        this.employeesRepository = employeesRepository;
    }

    public SalaryDTO createSalary(SalaryDTO dto) {
        Optional<Employees> optionalEmployee = employeesRepository.findById(dto.getEmployeeId());
        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("Employee not found");
        }

        // Calculate net salary
        BigDecimal netSalary = dto.getBaseSalary().add(dto.getBonus()).subtract(dto.getTax());
        if (netSalary.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Net salary cannot be negative");
        }

        SalaryEntity entity = new SalaryEntity();
        entity.setEmployee(optionalEmployee.get());
        entity.setBaseSalary(dto.getBaseSalary());
        entity.setBonus(dto.getBonus());
        entity.setTax(dto.getTax());
        entity.setEffectiveDate(dto.getEffectiveDate());

        SalaryEntity saved = salaryRepository.save(entity);
        return convertToDTO(saved);
    }

    public List<SalaryDTO> getSalaryHistoryByEmployeeId(Long employeeId) {
        return salaryRepository
                .findByEmployeeIdOrderByEffectiveDateDesc(employeeId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SalaryDTO> getAllSalaries() {
        return salaryRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SalaryDTO getSalaryById(Long id) {
        return salaryRepository
                .findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    private SalaryDTO convertToDTO(SalaryEntity entity) {
        BigDecimal base = entity.getBaseSalary() != null ? entity.getBaseSalary() : BigDecimal.ZERO;
        BigDecimal bonus = entity.getBonus() != null ? entity.getBonus() : BigDecimal.ZERO;
        BigDecimal tax = entity.getTax() != null ? entity.getTax() : BigDecimal.ZERO;

        BigDecimal netSalary = base.add(bonus).subtract(tax);

        return new SalaryDTO(
                entity.getId(),
                entity.getEmployee().getId(),
                base,
                bonus,
                tax,
                entity.getEffectiveDate(),
                netSalary // set net salary here
        );
    }

    public SalaryDTO updateSalary(Long id, SalaryDTO dto) {
        SalaryEntity existing = salaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salary record not found"));

        // Optional: Validate if employeeId is same or allow changing it
        Employees employee = employeesRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existing.setEmployee(employee);
        existing.setBaseSalary(dto.getBaseSalary());
        existing.setBonus(dto.getBonus());
        existing.setTax(dto.getTax());
        existing.setEffectiveDate(dto.getEffectiveDate());

        SalaryEntity updated = salaryRepository.save(existing);

        return convertToDTO(updated);
    }

}
