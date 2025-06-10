package com.example.employees.Salary;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salaries")
public class SalaryController {

    private final SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @PostMapping
    public ResponseEntity<SalaryDTO> createSalary(@Valid @RequestBody SalaryDTO dto) {
        return ResponseEntity.ok(salaryService.createSalary(dto));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<SalaryDTO>> getSalaryHistory(@PathVariable Long employeeId) {
        return ResponseEntity.ok(salaryService.getSalaryHistoryByEmployeeId(employeeId));
    }

    @GetMapping
    public ResponseEntity<List<SalaryDTO>> getAllSalaries() {
        return ResponseEntity.ok(salaryService.getAllSalaries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaryDTO> getSalaryById(@PathVariable Long id) {
        SalaryDTO salary = salaryService.getSalaryById(id);
        return salary != null ? ResponseEntity.ok(salary) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaryDTO> updateSalary(
            @PathVariable Long id,
            @Valid @RequestBody SalaryDTO dto) {
        return ResponseEntity.ok(salaryService.updateSalary(id, dto));
    }

}
