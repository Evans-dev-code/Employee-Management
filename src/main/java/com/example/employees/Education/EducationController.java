package com.example.employees.Education;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/education")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") // you already said this is okay ✅
public class EducationController {

    private final EducationService educationService;
    private final EducationRepository educationRepository; // ✅ Add this line

    @PostMapping("/upload")
    public ResponseEntity<?> uploadEducation(
            @RequestParam Long employeeId,
            @RequestParam String degreeName,
            @RequestParam String institution,
            @RequestParam Integer graduationYear,
            @RequestParam MultipartFile file
    ) {
        try {
            EducationDTO dto = educationService.uploadEducation(employeeId, degreeName, institution, graduationYear, file);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id) {
        try {
            FileSystemResource resource = new FileSystemResource(educationService.getFile(id));

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename());

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed to download file: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEducation(
            @PathVariable Long id,
            @RequestParam String degreeName,
            @RequestParam String institution,
            @RequestParam Integer graduationYear,
            @RequestParam(required = false) MultipartFile file
    ) {
        try {
            EducationDTO dto = educationService.updateEducation(id, degreeName, institution, graduationYear, file);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Update failed: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEducation(@PathVariable Long id) {
        try {
            educationService.deleteEducation(id);
            return ResponseEntity.ok("Education record deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Delete failed: " + e.getMessage());
        }
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> getEducationByEmployeeId(@PathVariable Long employeeId) {
        try {
            List<EducationEntity> entities = educationService.getEducationByEmployeeId(employeeId);

            List<EducationDTO> dtos = entities.stream().map(entity -> EducationDTO.builder()
                    .id(entity.getId())
                    .degreeName(entity.getDegreeName())
                    .institution(entity.getInstitution())
                    .graduationYear(entity.getGraduationYear())
                    .fileDownloadUrl("http://localhost:8080/api/education/download/" + entity.getId()) // 👈 correct URL
                    .uploadedAt(entity.getUploadedAt().toString())
                    .employeeId(entity.getEmployee().getId())
                    .build()
            ).toList();

            return ResponseEntity.ok(dtos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching education records: " + e.getMessage());
        }
    }

}
