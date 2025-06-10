package com.example.employees.Position;

import com.example.employees.Department.DepartmentEntity;
import com.example.employees.Department.DepartmentRepository;
import com.example.employees.Employees.Employees;
import com.example.employees.Employees.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {

    private final PositionRepository positionRepository;
    private final EmployeesRepository employeesRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository,
                           EmployeesRepository employeesRepository,
                           DepartmentRepository departmentRepository) {
        this.positionRepository = positionRepository;
        this.employeesRepository = employeesRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<PositionDTO> getAllPositions() {
        return positionRepository.findAll().stream().map(this::convertToDTO).toList();
    }

    public PositionDTO getPositionById(Long id) {
        return positionRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public PositionDTO createPosition(PositionDTO dto) {
        if (dto.getDepartmentId() == null) {
            throw new IllegalArgumentException("Department ID is required to create a position.");
        }

        DepartmentEntity department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        PositionEntity entity = new PositionEntity();
        entity.setTitle(dto.getTitle());
        entity.setDepartment(department);

        PositionEntity saved = positionRepository.save(entity);
        return convertToDTO(saved);
    }

    public PositionDTO updatePosition(Long id, PositionDTO dto) {
        return positionRepository.findById(id).map(existing -> {
            existing.setTitle(dto.getTitle());

            if (dto.getDepartmentId() != null) {
                DepartmentEntity dept = departmentRepository.findById(dto.getDepartmentId())
                        .orElseThrow(() -> new IllegalArgumentException("Department not found"));
                existing.setDepartment(dept);
            }

            return convertToDTO(positionRepository.save(existing));
        }).orElse(null);
    }

    public void deletePosition(Long id) {
        positionRepository.deleteById(id);
    }

    public Employees assignPositionToEmployee(Long employeeId, Long positionId) {
        Optional<Employees> employeeOpt = employeesRepository.findById(employeeId);
        Optional<PositionEntity> positionOpt = positionRepository.findById(positionId);

        if (employeeOpt.isPresent() && positionOpt.isPresent()) {
            Employees employee = employeeOpt.get();
            employee.setPosition(positionOpt.get());
            return employeesRepository.save(employee);
        }
        return null;
    }

    public List<PositionDTO> getPositionsByDepartmentId(Long departmentId) {
        return positionRepository.findByDepartmentId(departmentId).stream()
                .map(this::convertToDTO)
                .toList();
    }

    private PositionDTO convertToDTO(PositionEntity entity) {
        return new PositionDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDepartment() != null ? entity.getDepartment().getId() : null,
                entity.getDepartment() != null ? entity.getDepartment().getName() : null
        );
    }
}

