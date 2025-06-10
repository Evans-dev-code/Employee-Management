package com.example.employees.WorkHistory;

import com.example.employees.Enums.EmploymentType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkHistoryDTO {

    private Long id;

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Position title is required")
    private String positionTitle;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    private LocalDate endDate;

    @NotBlank(message = "Responsibilities field cannot be blank")
    private String responsibilities;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Employment type is required")
    private EmploymentType employmentType;

    @Size(max = 100, message = "Supervisor name must be under 100 characters")
    private String supervisorName;

    @Size(max = 100, message = "Supervisor contact must be under 100 characters")
    private String supervisorContact;

    @Size(max = 255, message = "Reason for leaving must be under 255 characters")
    private String reasonForLeaving;
}
