package com.example.employees.Position;

import com.example.employees.Employees.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
@CrossOrigin(origins = "*")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping
    public List<PositionDTO> getAllPositions() {
        return positionService.getAllPositions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionDTO> getPositionById(@PathVariable Long id) {
        PositionDTO position = positionService.getPositionById(id);
        return position != null ? ResponseEntity.ok(position) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public PositionDTO createPosition(@RequestBody PositionDTO dto) {
        return positionService.createPosition(dto);
    }

    @PutMapping("/{id}")
    public PositionDTO updatePosition(@PathVariable Long id, @RequestBody PositionDTO dto) {
        return positionService.updatePosition(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable Long id) {
        positionService.deletePosition(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/assign/{employeeId}/{positionId}")
    public Employees assignPosition(@PathVariable Long employeeId, @PathVariable Long positionId) {
        return positionService.assignPositionToEmployee(employeeId, positionId);
    }

    @GetMapping("/by-department/{deptId}")
    public List<PositionDTO> getPositionsByDepartment(@PathVariable Long deptId) {
        return positionService.getPositionsByDepartmentId(deptId);
    }
}

