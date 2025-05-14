package com.example.employees.Employees;

import com.example.employees.Department.DepartmentEntity;
import com.example.employees.Department.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeMapper(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Converts Employees entity to EmployeeDTO
    public EmployeeDTO toEmployeeDTO(Employees employee) {
        if (employee == null) return null;

        Long departmentId = null;
        String departmentName = null;

        if (employee.getDepartment() != null) {
            departmentId = employee.getDepartment().getId();
            departmentName = employee.getDepartment().getName();
        }

        return new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmailId(),
                departmentId,
                departmentName
        );
    }


    // Converts EmployeeDTO to Employees entity
    public Employees toEmployeeEntity(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) return null;

        Employees employee = new Employees();

        // Set ID only if it is not null (useful for updates)
        if (employeeDTO.getId() != null) {
            employee.setId(employeeDTO.getId());
        }

        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmailId(employeeDTO.getEmailId());

        Long deptId = employeeDTO.getDepartmentId();
        if (deptId != null) {
            departmentRepository.findById(deptId).ifPresent(employee::setDepartment);
        } else {
            employee.setDepartment(null); // explicitly set to null if no department ID provided
        }

        return employee;
    }
}
