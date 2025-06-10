package com.example.employees.Employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeesService {

    private final EmployeesRepository employeesRepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeesService(EmployeesRepository employeesRepository, EmployeeMapper employeeMapper) {
        this.employeesRepository = employeesRepository;
        this.employeeMapper = employeeMapper;
    }

    public List<EmployeeDTO> getByFirstName(String firstName) {
        return employeesRepository.findByFirstName(firstName).stream()
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getByEmail(String email) {
        Employees employee = employeesRepository.findByEmailId(email);
        return employee != null ? employeeMapper.toEmployeeDTO(employee) : null;
    }

    public List<EmployeeDTO> searchByLastName(String partialLastName) {
        return employeesRepository.findByLastNameContaining(partialLastName).stream()
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employees employee = employeeMapper.toEmployeeEntity(employeeDTO);
        Employees saved = employeesRepository.save(employee);
        return employeeMapper.toEmployeeDTO(saved);
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeesRepository.findAll().stream()
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeesRepository.findById(id)
                .map(employee -> {
                    EmployeeDTO dto = employeeMapper.toEmployeeDTO(employee);

                    if (employee.getPosition() != null && employee.getPosition().getDepartment() != null) {
                        dto.setDepartmentId(employee.getPosition().getDepartment().getId());
                        dto.setDepartmentName(employee.getPosition().getDepartment().getName());
                    }

                    return dto;
                });
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        return employeesRepository.findById(id)
                .map(existing -> {
                    existing.setFirstName(dto.getFirstName());
                    existing.setLastName(dto.getLastName());
                    existing.setEmailId(dto.getEmailId());

                    // Update position, if needed
                    Employees updated = employeeMapper.updateEmployeeEntity(existing, dto);
                    updated = employeesRepository.save(updated);
                    return employeeMapper.toEmployeeDTO(updated);
                })
                .orElse(null);
    }

    public void deleteEmployee(Long id) {
        employeesRepository.deleteById(id);
    }

    // ✅ New method: Get employees by department ID
    public List<EmployeeDTO> getEmployeesByDepartmentId(Long departmentId) {
        return employeesRepository.findByPosition_Department_Id(departmentId)
                .stream()
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    // ✅ New method: Get employees by position ID
    public List<EmployeeDTO> getEmployeesByPositionId(Long positionId) {
        return employeesRepository.findByPosition_Id(positionId)
                .stream()
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }
}
