package com.example.employees.Salary;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SalaryDTO {

    private Long id;

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotNull(message = "Base salary is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Base salary must be greater than zero")
    private BigDecimal baseSalary;

    @NotNull(message = "Bonus is required")
    @DecimalMin(value = "0.0", message = "Bonus must be zero or positive")
    private BigDecimal bonus;

    @NotNull(message = "Tax is required")
    @DecimalMin(value = "0.0", message = "Tax must be zero or positive")
    private BigDecimal tax;

    @NotNull(message = "Effective date is required")
    @PastOrPresent(message = "Effective date cannot be in the future")
    private LocalDate effectiveDate;

    private BigDecimal netSalary;  // calculated, so no validation needed

    public SalaryDTO() {}

    public SalaryDTO(Long id, Long employeeId, BigDecimal baseSalary, BigDecimal bonus, BigDecimal tax, LocalDate effectiveDate, BigDecimal netSalary) {
        this.id = id;
        this.employeeId = employeeId;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.tax = tax;
        this.effectiveDate = effectiveDate;
        this.netSalary = netSalary;
    }

// Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public BigDecimal getBaseSalary() { return baseSalary; }
    public void setBaseSalary(BigDecimal baseSalary) { this.baseSalary = baseSalary; }

    public BigDecimal getBonus() { return bonus; }
    public void setBonus(BigDecimal bonus) { this.bonus = bonus; }

    public BigDecimal getTax() { return tax; }
    public void setTax(BigDecimal tax) { this.tax = tax; }

    public LocalDate getEffectiveDate() { return effectiveDate; }
    public void setEffectiveDate(LocalDate effectiveDate) { this.effectiveDate = effectiveDate; }

    public BigDecimal getNetSalary() { return netSalary; }
    public void setNetSalary(BigDecimal netSalary) { this.netSalary = netSalary; }
}
