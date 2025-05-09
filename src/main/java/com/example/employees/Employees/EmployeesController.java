package com.example.employees.Employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    @Autowired
    private EmployeesService employeesService;

    @GetMapping("/firstName/{name}")
    public List<Employees> getByFirstName(@PathVariable String name) {
        return employeesService.getByFirstName(name);
    }

    @GetMapping("/email")
    public Employees getByEmail(@RequestParam String email) {
        return employeesService.getByEmail(email);
    }

    @GetMapping("/searchLastName")
    public List<Employees> searchByLastName(@RequestParam String keyword) {
        return employeesService.searchByLastName(keyword);
    }

    @PostMapping
    public Employees createEmployee(@RequestBody Employees employee) {
        return employeesService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employees> getAllEmployees() {
        return employeesService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Optional<Employees> getEmployeeById(@PathVariable Long id) {
        return employeesService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public Employees updateEmployee(@PathVariable Long id, @RequestBody Employees employee) {
        return employeesService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeesService.deleteEmployee(id);
    }
}