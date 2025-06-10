package com.example.employees.PerformanceReview;

import com.example.employees.Employees.Employees;
import com.example.employees.Employees.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PerformanceReviewService {

    @Autowired
    private PerformanceReviewRepository reviewRepository;

    @Autowired
    private EmployeesRepository employeesRepository;

    // Helper methods to convert Entity <-> DTO
    private PerformanceReviewDTO mapToDTO(PerformanceReviewEntity entity) {
        PerformanceReviewDTO dto = new PerformanceReviewDTO();
        dto.setId(entity.getId());
        dto.setEmployeeId(entity.getEmployee().getId());
        dto.setReviewDate(entity.getReviewDate());
        dto.setReviewer(entity.getReviewer());
        dto.setScore(entity.getScore());
        dto.setComments(entity.getComments());
        dto.setNextReviewDate(entity.getNextReviewDate());
        return dto;
    }

    private PerformanceReviewEntity mapToEntity(PerformanceReviewDTO dto) {
        PerformanceReviewEntity entity = new PerformanceReviewEntity();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        Employees employee = employeesRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + dto.getEmployeeId()));
        entity.setEmployee(employee);
        entity.setReviewDate(dto.getReviewDate());
        entity.setReviewer(dto.getReviewer());
        entity.setScore(dto.getScore());
        entity.setComments(dto.getComments());
        entity.setNextReviewDate(dto.getNextReviewDate());
        return entity;
    }

    // CRUD methods
    public List<PerformanceReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<PerformanceReviewDTO> getReviewsByEmployeeId(Long employeeId) {
        return reviewRepository.findByEmployeeId(employeeId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PerformanceReviewDTO getReviewById(Long id) {
        PerformanceReviewEntity entity = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Performance review not found with id " + id));
        return mapToDTO(entity);
    }

    public PerformanceReviewDTO createReview(PerformanceReviewDTO dto) {
        PerformanceReviewEntity entity = mapToEntity(dto);
        PerformanceReviewEntity saved = reviewRepository.save(entity);
        return mapToDTO(saved);
    }

    public PerformanceReviewDTO updateReview(Long id, PerformanceReviewDTO dto) {
        PerformanceReviewEntity existing = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Performance review not found with id " + id));
        // Map updated fields
        existing.setReviewDate(dto.getReviewDate());
        existing.setReviewer(dto.getReviewer());
        existing.setScore(dto.getScore());
        existing.setComments(dto.getComments());
        existing.setNextReviewDate(dto.getNextReviewDate());

        PerformanceReviewEntity updated = reviewRepository.save(existing);
        return mapToDTO(updated);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
