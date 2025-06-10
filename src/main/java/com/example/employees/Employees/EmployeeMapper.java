package com.example.employees.Employees;

import com.example.employees.Position.PositionEntity;
import com.example.employees.Position.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    private final PositionRepository positionRepository;

    @Autowired
    public EmployeeMapper(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    // Converts Employees entity to EmployeeDTO
    public EmployeeDTO toEmployeeDTO(Employees employee) {
        if (employee == null) return null;

        Long positionId = null;
        String positionTitle = null;
        Long departmentId = null;
        String departmentName = null;

        if (employee.getPosition() != null) {
            PositionEntity position = employee.getPosition();
            positionId = position.getId();
            positionTitle = position.getTitle();

            if (position.getDepartment() != null) {
                departmentId = position.getDepartment().getId();
                departmentName = position.getDepartment().getName();
            }
        }

        return new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmailId(),
                departmentId,
                departmentName,
                positionId,
                positionTitle
        );
    }

    // Converts EmployeeDTO to Employees entity
    public Employees toEmployeeEntity(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) return null;

        Employees employee = new Employees();

        if (employeeDTO.getId() != null) {
            employee.setId(employeeDTO.getId());
        }

        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmailId(employeeDTO.getEmailId());

        Long positionId = employeeDTO.getPositionId();
        if (positionId != null) {
            positionRepository.findById(positionId).ifPresent(employee::setPosition);
        } else {
            employee.setPosition(null);
        }

        return employee;
    }

    // ✅ New method: Updates existing Employees entity using EmployeeDTO
    public Employees updateEmployeeEntity(Employees existing, EmployeeDTO dto) {
        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setEmailId(dto.getEmailId());

        if (dto.getPositionId() != null) {
            positionRepository.findById(dto.getPositionId()).ifPresent(existing::setPosition);
        } else {
            existing.setPosition(null);
        }

        return existing;
    }
}
