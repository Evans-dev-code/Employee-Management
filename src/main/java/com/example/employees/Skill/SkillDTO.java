package com.example.employees.Skill;

import com.example.employees.Enums.ProficiencyLevel;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillDTO {

    private Long id;
    private Long employeeId;
    private String name;
    private ProficiencyLevel proficiencyLevel;
    private int yearsOfExperience;
    private boolean certified;
    private String certificationName;
}
