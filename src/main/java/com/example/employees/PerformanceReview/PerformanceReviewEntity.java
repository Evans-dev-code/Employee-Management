package com.example.employees.PerformanceReview;

import com.example.employees.Employees.Employees;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "performance_reviews")
public class PerformanceReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many reviews can belong to one employee
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employees employee;

    @Column(name = "review_date", nullable = false)
    private LocalDate reviewDate;

    @Column(name = "reviewer", nullable = false)
    private String reviewer;

    @Column(name = "score")
    private Integer score; // or Float/Double depending on scale

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Column(name = "next_review_date")
    private LocalDate nextReviewDate;

    public PerformanceReviewEntity() {
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDate getNextReviewDate() {
        return nextReviewDate;
    }

    public void setNextReviewDate(LocalDate nextReviewDate) {
        this.nextReviewDate = nextReviewDate;
    }
}
