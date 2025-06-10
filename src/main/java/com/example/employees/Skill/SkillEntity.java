package com.example.employees.Skill;

import com.example.employees.Enums.ProficiencyLevel;
import com.example.employees.Employees.Employees;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employees employee;

    private String name;

    @Enumerated(EnumType.STRING)
    private ProficiencyLevel proficiencyLevel;

    private int yearsOfExperience;

    private boolean certified;

    private String certificationName;
}
