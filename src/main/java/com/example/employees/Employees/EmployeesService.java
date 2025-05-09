package com.example.employees.Employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesService {

    @Autowired
    private EmployeesRepository employeesRepository;

    public List<Employees> getByFirstName(String name) {
        return employeesRepository.findByFirstName(name);
    }

    public Employees getByEmail(String email) {
        return employeesRepository.findByEmailId(email);
    }

    public List<Employees> searchByLastName(String part) {
        return employeesRepository.findByLastNameContaining(part);
    }

    public Employees saveEmployee(Employees employee) {
        return employeesRepository.save(employee);
    }
    public List<Employees> getAllEmployees() {
        return employeesRepository.findAll();
    }

    public Optional<Employees> getEmployeeById(Long id) {
        return employeesRepository.findById(id);
    }

    public Employees updateEmployee(Long id, Employees updatedEmployee) {
        return employeesRepository.findById(id).map(employee -> {
            employee.setFirstName(updatedEmployee.getFirstName());
            employee.setLastName(updatedEmployee.getLastName());
            employee.setEmailId(updatedEmployee.getEmailId());

            return employeesRepository.save(employee);
        }).orElse(null);
    }

    public void deleteEmployee(Long id) {
        employeesRepository.deleteById(id);
    }
}