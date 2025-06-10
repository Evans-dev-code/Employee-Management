package com.example.employees.WorkHistory;

import com.example.employees.Employees.Employees;
import com.example.employees.Enums.EmploymentType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employees employee;

    private String companyName;

    private String positionTitle;

    private LocalDate startDate;

    private LocalDate endDate;

    @Column(columnDefinition = "TEXT")
    private String responsibilities;

    private String location;

    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    private String supervisorName;

    private String supervisorContact;

    private String reasonForLeaving;
}
