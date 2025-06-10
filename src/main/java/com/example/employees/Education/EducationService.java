package com.example.employees.Education;

import com.example.employees.Employees.Employees;
import com.example.employees.Employees.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationService {
    private final EducationRepository educationRepository;
    private final EmployeesRepository employeesRepository;

    @Value("${upload.education.dir}")
    private String uploadDir;

    public EducationDTO uploadEducation(Long employeeId, String degreeName, String institution, Integer graduationYear, MultipartFile file) throws IOException {
        // Find employee
        Employees employee = employeesRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Create upload folder if it doesn't exist
        File uploadPath = new File(uploadDir);
        if (!uploadPath.exists()) uploadPath.mkdirs();

        // Generate file name and full path
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filePath = uploadDir + File.separator + fileName;

        // Save the file to disk
        file.transferTo(new File(filePath));

        // Build and save education entity
        EducationEntity education = EducationEntity.builder()
                .employee(employee)
                .degreeName(degreeName)
                .institution(institution)
                .graduationYear(graduationYear)
                .fileName(fileName)
                .filePath(filePath)
                .uploadedAt(LocalDateTime.now())
                .build();

        education = educationRepository.save(education);

        return mapToDTO(education);
    }

    public EducationDTO getEducation(Long id) {
        EducationEntity education = educationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Education not found"));
        return mapToDTO(education);
    }

    private EducationDTO mapToDTO(EducationEntity edu) {
        return EducationDTO.builder()
                .id(edu.getId())
                .degreeName(edu.getDegreeName())
                .institution(edu.getInstitution())
                .graduationYear(edu.getGraduationYear())
                .fileDownloadUrl("/api/education/download/" + edu.getId())
                .uploadedAt(edu.getUploadedAt().toString())
                .employeeId(edu.getEmployee().getId())
                .build();
    }

    public File getFile(Long id) {
        EducationEntity education = educationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Education not found"));

        return new File(education.getFilePath());
    }

    public EducationDTO updateEducation(Long id, String degreeName, String institution, Integer graduationYear, MultipartFile file) throws IOException {
        EducationEntity education = educationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Education record not found"));

        education.setDegreeName(degreeName);
        education.setInstitution(institution);
        education.setGraduationYear(graduationYear);

        if (file != null && !file.isEmpty()) {
            // Delete old file
            File oldFile = new File(education.getFilePath());
            if (oldFile.exists()) oldFile.delete();

            // Save new file
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = uploadDir + File.separator + fileName;
            file.transferTo(new File(filePath));

            education.setFileName(fileName);
            education.setFilePath(filePath);
            education.setUploadedAt(LocalDateTime.now());
        }

        education = educationRepository.save(education);
        return mapToDTO(education);
    }

    public void deleteEducation(Long id) {
        EducationEntity education = educationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Education not found"));

        // Delete file from disk
        File file = new File(education.getFilePath());
        if (file.exists()) file.delete();

        // Delete from DB
        educationRepository.deleteById(id);
    }

    public List<EducationEntity> getEducationByEmployeeId(Long employeeId) {
        return educationRepository.findByEmployeeId(employeeId);
    }


}
