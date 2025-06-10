package com.example.employees.EmergencyContact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees/{employeeId}/contacts")
public class EmergencyContactController {

    @Autowired
    private EmergencyContactService contactService;

    @GetMapping
    public List<EmergencyContactDTO> getAll(@PathVariable Long employeeId) {
        return contactService.getContactsByEmployeeId(employeeId);
    }

    @PostMapping
    public EmergencyContactDTO create(@PathVariable Long employeeId, @RequestBody EmergencyContactDTO dto) {
        dto.setEmployeeId(employeeId);
        return contactService.createContact(dto);
    }

    @PutMapping("/{contactId}")
    public EmergencyContactDTO update(@PathVariable Long contactId, @RequestBody EmergencyContactDTO dto) {
        return contactService.updateContact(contactId, dto);
    }

    @DeleteMapping("/{contactId}")
    public void delete(@PathVariable Long contactId) {
        contactService.deleteContact(contactId);
    }
}
