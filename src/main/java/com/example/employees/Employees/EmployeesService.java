package com.example.employees.Employees;

import com.example.employees.Department.DepartmentEntity;
import com.example.employees.Department.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeesService {

    private final EmployeesRepository employeesRepository;
    private final EmployeeMapper employeeMapper;
    private final DepartmentRepository departmentRepository;  // Add DepartmentRepository

    @Autowired
    public EmployeesService(EmployeesRepository employeesRepository, EmployeeMapper employeeMapper, DepartmentRepository departmentRepository) {
        this.employeesRepository = employeesRepository;
        this.employeeMapper = employeeMapper;
        this.departmentRepository = departmentRepository;
    }

    // Get employees by first name
    public List<EmployeeDTO> getByFirstName(String name) {
        return employeesRepository.findByFirstName(name).stream()
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    // Get employee by email
    public EmployeeDTO getByEmail(String email) {
        Employees employee = employeesRepository.findByEmailId(email);
        return employee != null ? employeeMapper.toEmployeeDTO(employee) : null;
    }

    // Search employees by last name
    public List<EmployeeDTO> searchByLastName(String part) {
        return employeesRepository.findByLastNameContaining(part).stream()
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    // Save employee
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employees employee = employeeMapper.toEmployeeEntity(employeeDTO);
        Employees savedEmployee = employeesRepository.save(employee);
        return employeeMapper.toEmployeeDTO(savedEmployee);
    }

    // Get all employees
    public List<EmployeeDTO> getAllEmployees() {
        return employeesRepository.findAll().stream()
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeesRepository.findById(id)
                .map(employee -> {
                    EmployeeDTO employeeDTO = employeeMapper.toEmployeeDTO(employee);

                    // Set department details if present
                    if (employee.getDepartment() != null) {
                        DepartmentEntity department = employee.getDepartment();
                        employeeDTO.setDepartmentId(department.getId());
                        employeeDTO.setDepartmentName(department.getName());
                    }

                    return employeeDTO;
                });
    }


    // Update employee
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO updatedEmployeeDTO) {
        return employeesRepository.findById(id).map(employee -> {
            employee.setFirstName(updatedEmployeeDTO.getFirstName());
            employee.setLastName(updatedEmployeeDTO.getLastName());
            employee.setEmailId(updatedEmployeeDTO.getEmailId());

            // Handle department update if present
            if (updatedEmployeeDTO.getDepartmentId() != null) {
                employee.setDepartment(
                        employeeMapper.toEmployeeEntity(updatedEmployeeDTO).getDepartment()
                );
            }

            Employees updatedEmployee = employeesRepository.save(employee);
            return employeeMapper.toEmployeeDTO(updatedEmployee);
        }).orElse(null);
    }

    // Delete employee
    public void deleteEmployee(Long id) {
        employeesRepository.deleteById(id);
    }
}
