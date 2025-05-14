package com.example.employees.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentEntity saveDepartment(DepartmentEntity department) {
        return departmentRepository.save(department);
    }

    public List<DepartmentEntity> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<DepartmentEntity> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public DepartmentEntity updateDepartment(Long id, DepartmentEntity updatedDepartment) {
        Optional<DepartmentEntity> existing = departmentRepository.findById(id);
        if (existing.isPresent()) {
            DepartmentEntity department = existing.get();
            department.setName(updatedDepartment.getName());
            return departmentRepository.save(department);
        }
        return null;
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
