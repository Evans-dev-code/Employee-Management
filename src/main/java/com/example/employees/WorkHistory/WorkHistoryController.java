package com.example.employees.WorkHistory;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work-history")
@RequiredArgsConstructor
public class WorkHistoryController {

    private final WorkHistoryService workHistoryService;

    @PostMapping
    public ResponseEntity<WorkHistoryDTO> addWorkHistory(@Valid @RequestBody WorkHistoryDTO dto) {
        return ResponseEntity.ok(workHistoryService.saveWorkHistory(dto));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<WorkHistoryDTO>> getByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(workHistoryService.getWorkHistoryByEmployeeId(employeeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workHistoryService.deleteWorkHistory(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkHistoryDTO> updateWorkHistory(@PathVariable Long id, @Valid @RequestBody WorkHistoryDTO dto) {
        return ResponseEntity.ok(workHistoryService.updateWorkHistory(id, dto));
    }
}
