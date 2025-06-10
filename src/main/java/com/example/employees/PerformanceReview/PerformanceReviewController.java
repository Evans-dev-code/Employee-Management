package com.example.employees.PerformanceReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performance-reviews")
public class PerformanceReviewController {

    @Autowired
    private PerformanceReviewService reviewService;

    @GetMapping
    public List<PerformanceReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/employee/{employeeId}")
    public List<PerformanceReviewDTO> getReviewsByEmployee(@PathVariable Long employeeId) {
        return reviewService.getReviewsByEmployeeId(employeeId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceReviewDTO> getReviewById(@PathVariable Long id) {
        PerformanceReviewDTO dto = reviewService.getReviewById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PerformanceReviewDTO> createReview(@RequestBody PerformanceReviewDTO dto) {
        PerformanceReviewDTO created = reviewService.createReview(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerformanceReviewDTO> updateReview(@PathVariable Long id, @RequestBody PerformanceReviewDTO dto) {
        PerformanceReviewDTO updated = reviewService.updateReview(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
