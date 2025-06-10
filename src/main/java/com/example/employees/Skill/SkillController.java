package com.example.employees.Skill;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @PostMapping
    public ResponseEntity<SkillDTO> addSkill(@RequestBody SkillDTO dto) {
        return ResponseEntity.ok(skillService.saveSkill(dto));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<SkillDTO>> getByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(skillService.getSkillsByEmployee(employeeId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillDTO> update(@PathVariable Long id, @RequestBody SkillDTO dto) {
        return ResponseEntity.ok(skillService.updateSkill(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }
}
