package com.example.employees.Salary;

import com.example.employees.Employees.Employees;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "salary")
public class SalaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonBackReference
    private Employees employee;

    private BigDecimal baseSalary;
    private BigDecimal bonus;
    private BigDecimal tax;
    private LocalDate effectiveDate;

    public SalaryEntity() {}

    public SalaryEntity(Employees employee, BigDecimal baseSalary, BigDecimal bonus, BigDecimal tax, LocalDate effectiveDate) {
        this.employee = employee;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.tax = tax;
        this.effectiveDate = effectiveDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Employees getEmployee() { return employee; }
    public void setEmployee(Employees employee) { this.employee = employee; }

    public BigDecimal getBaseSalary() { return baseSalary; }
    public void setBaseSalary(BigDecimal baseSalary) { this.baseSalary = baseSalary; }

    public BigDecimal getBonus() { return bonus; }
    public void setBonus(BigDecimal bonus) { this.bonus = bonus; }

    public BigDecimal getTax() { return tax; }
    public void setTax(BigDecimal tax) { this.tax = tax; }

    public LocalDate getEffectiveDate() { return effectiveDate; }
    public void setEffectiveDate(LocalDate effectiveDate) { this.effectiveDate = effectiveDate; }

}
