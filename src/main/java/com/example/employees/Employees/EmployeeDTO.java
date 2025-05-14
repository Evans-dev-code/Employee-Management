package com.example.employees.Employees;

public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private Long departmentId;
    private String departmentName; // ✅ New field

    public EmployeeDTO() {}

    public EmployeeDTO(Long id, String firstName, String lastName, String emailId, Long departmentId, String departmentName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    // Getters and setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmailId() { return emailId; }

    public void setEmailId(String emailId) { this.emailId = emailId; }

    public Long getDepartmentId() { return departmentId; }

    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }

    public String getDepartmentName() { return departmentName; }

    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
}
