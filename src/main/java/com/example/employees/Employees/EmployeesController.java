package com.example.employees.Employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors; // <-- Importing Collectors

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private EmployeeMapper employeeMapper;

    // Get employees by first name
    @GetMapping("/firstName/{name}")
    public List<EmployeeDTO> getByFirstName(@PathVariable String name) {
        return employeesService.getByFirstName(name);
    }

    // Get employee by email
    @GetMapping("/email")
    public EmployeeDTO getByEmail(@RequestParam String email) {
        return employeesService.getByEmail(email);
    }

    // Search employees by part of last name
    @GetMapping("/searchLastName")
    public List<EmployeeDTO> searchByLastName(@RequestParam String keyword) {
        return employeesService.searchByLastName(keyword);
    }

    // Create a new employee
    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeesService.saveEmployee(employeeDTO);
    }

    // Get all employees with department name included
    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        // Fetching employee entities using the correct service method
        List<EmployeeDTO> employees = employeesService.getAllEmployees();

        // Return the list of EmployeeDTO
        return employees;
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        Optional<EmployeeDTO> employeeDTO = employeesService.getEmployeeById(id);

        if (employeeDTO.isPresent()) {
            return ResponseEntity.ok(employeeDTO.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Update employee by ID
    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeesService.updateEmployee(id, employeeDTO);
    }

    // Delete employee by ID
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeesService.deleteEmployee(id);
    }
}
