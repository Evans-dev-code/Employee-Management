package com.example.employees.EmergencyContact;

import com.example.employees.Employees.Employees;
import com.example.employees.Employees.EmployeesRepository;
import com.example.employees.Employees.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmergencyContactService {

    @Autowired
    private EmergencyContactRepository contactRepo;

    @Autowired
    private EmployeesRepository employeeRepo;

    public List<EmergencyContactDTO> getContactsByEmployeeId(Long employeeId) {
        return contactRepo.findByEmployeeId(employeeId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public EmergencyContactDTO createContact(EmergencyContactDTO dto) {
        Employees employee = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        EmergencyContactEntity entity = toEntity(dto);
        entity.setEmployee(employee);
        EmergencyContactEntity saved = contactRepo.save(entity);
        return toDTO(saved);
    }

    public EmergencyContactDTO updateContact(Long id, EmergencyContactDTO dto) {
        EmergencyContactEntity entity = contactRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        entity.setName(dto.getName());
        entity.setRelationship(dto.getRelationship());
        entity.setPhone(dto.getPhone());

        return toDTO(contactRepo.save(entity));
    }

    public void deleteContact(Long id) {
        contactRepo.deleteById(id);
    }

    // Helpers
    private EmergencyContactDTO toDTO(EmergencyContactEntity entity) {
        EmergencyContactDTO dto = new EmergencyContactDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setRelationship(entity.getRelationship());
        dto.setPhone(entity.getPhone());
        dto.setEmployeeId(entity.getEmployee().getId());
        return dto;
    }

    private EmergencyContactEntity toEntity(EmergencyContactDTO dto) {
        EmergencyContactEntity entity = new EmergencyContactEntity();
        entity.setName(dto.getName());
        entity.setRelationship(dto.getRelationship());
        entity.setPhone(dto.getPhone());
        return entity;
    }
}
