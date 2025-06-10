package com.example.employees.Employees;

import com.example.employees.Department.DepartmentEntity;
import com.example.employees.EmergencyContact.EmergencyContactEntity;
import com.example.employees.Position.PositionEntity;
import com.example.employees.Salary.SalaryEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id")
    private String emailId;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private PositionEntity position;

    // 🔁 One-to-Many with SalaryEntity
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SalaryEntity> salaries;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<EmergencyContactEntity> emergencyContacts;

    public List<EmergencyContactEntity> getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(List<EmergencyContactEntity> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }

    public Employees() {
    }

    public Employees(String firstName, String lastName, String emailId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }

    // 🔧 Getters & Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public PositionEntity getPosition() {
        return position;
    }

    public void setPosition(PositionEntity position) {
        this.position = position;
    }

    public List<SalaryEntity> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<SalaryEntity> salaries) {
        this.salaries = salaries;
    }
}
