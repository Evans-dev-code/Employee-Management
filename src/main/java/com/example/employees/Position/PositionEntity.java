package com.example.employees.Position;

import com.example.employees.Department.DepartmentEntity;
import com.example.employees.Employees.Employees;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "positions")
public class PositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "position")
    @JsonIgnore
    private List<Employees> employees;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<Employees> getEmployees() { return employees; }
    public void setEmployees(List<Employees> employees) { this.employees = employees; }

    public DepartmentEntity getDepartment() { return department; }
    public void setDepartment(DepartmentEntity department) { this.department = department; }
}


