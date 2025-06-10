package com.example.employees.Department;

import com.example.employees.Employees.Employees;
import com.example.employees.Position.PositionEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PositionEntity> positions; // ✅ New

    // Constructors
    public DepartmentEntity() {}
    public DepartmentEntity(String name) { this.name = name; }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<PositionEntity> getPositions() { return positions; } // ✅ New
    public void setPositions(List<PositionEntity> positions) { this.positions = positions; } // ✅ New
}
