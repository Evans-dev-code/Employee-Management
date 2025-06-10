package com.example.employees.Education;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationDTO {

    private Long id;
    private String degreeName;
    private String institution;
    private Integer graduationYear;
    private String fileDownloadUrl;
    private String uploadedAt;
    private Long employeeId;
}
