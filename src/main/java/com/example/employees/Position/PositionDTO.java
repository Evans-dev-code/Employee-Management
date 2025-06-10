package com.example.employees.Position;

public class PositionDTO {
    private Long id;
    private String title;
    private Long departmentId;
    private String departmentName;

    // Constructors
    public PositionDTO() {}

    public PositionDTO(Long id, String title, Long departmentId, String departmentName) {
        this.id = id;
        this.title = title;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
}
