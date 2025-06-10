package com.example.employees.Skill;

import com.example.employees.Employees.Employees;
import com.example.employees.Employees.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;
    private final EmployeesRepository employeeRepository;

    public SkillDTO saveSkill(SkillDTO dto) {
        Employees employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        SkillEntity skill = SkillEntity.builder()
                .employee(employee)
                .name(dto.getName())
                .proficiencyLevel(dto.getProficiencyLevel())
                .yearsOfExperience(dto.getYearsOfExperience())
                .certified(dto.isCertified())
                .certificationName(dto.getCertificationName())
                .build();

        return mapToDTO(skillRepository.save(skill));
    }

    public List<SkillDTO> getSkillsByEmployee(Long employeeId) {
        return skillRepository.findByEmployeeId(employeeId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public SkillDTO updateSkill(Long id, SkillDTO dto) {
        SkillEntity existing = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        Employees employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existing.setEmployee(employee);
        existing.setName(dto.getName());
        existing.setProficiencyLevel(dto.getProficiencyLevel());
        existing.setYearsOfExperience(dto.getYearsOfExperience());
        existing.setCertified(dto.isCertified());
        existing.setCertificationName(dto.getCertificationName());

        return mapToDTO(skillRepository.save(existing));
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    private SkillDTO mapToDTO(SkillEntity entity) {
        return SkillDTO.builder()
                .id(entity.getId())
                .employeeId(entity.getEmployee().getId())
                .name(entity.getName())
                .proficiencyLevel(entity.getProficiencyLevel())
                .yearsOfExperience(entity.getYearsOfExperience())
                .certified(entity.isCertified())
                .certificationName(entity.getCertificationName())
                .build();
    }
}
