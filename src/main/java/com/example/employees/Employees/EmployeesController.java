package com.example.employees.Employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("/firstName/{name}")
    public List<EmployeeDTO> getByFirstName(@PathVariable String name) {
        return employeesService.getByFirstName(name);
    }

    @GetMapping("/email")
    public EmployeeDTO getByEmail(@RequestParam String email) {
        return employeesService.getByEmail(email);
    }

    @GetMapping("/searchLastName")
    public List<EmployeeDTO> searchByLastName(@RequestParam String keyword) {
        return employeesService.searchByLastName(keyword);
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeesService.saveEmployee(employeeDTO);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeesService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        Optional<EmployeeDTO> employeeDTO = employeesService.getEmployeeById(id);
        return employeeDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeesService.updateEmployee(id, employeeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeesService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-department/{deptId}")
    public List<EmployeeDTO> getEmployeesByDepartment(@PathVariable Long deptId) {
        return employeesService.getEmployeesByDepartmentId(deptId);
    }

    @GetMapping("/by-position/{positionId}")
    public List<EmployeeDTO> getEmployeesByPosition(@PathVariable Long positionId) {
        return employeesService.getEmployeesByPositionId(positionId);
    }

}
