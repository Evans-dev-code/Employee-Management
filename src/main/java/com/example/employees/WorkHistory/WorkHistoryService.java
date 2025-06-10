package com.example.employees.WorkHistory;

import com.example.employees.Employees.Employees;
import com.example.employees.Employees.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkHistoryService {

    private final WorkHistoryRepository workHistoryRepository;
    private final EmployeesRepository employeeRepository;

    public WorkHistoryDTO saveWorkHistory(WorkHistoryDTO dto) {
        Employees employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        WorkHistoryEntity workHistory = WorkHistoryEntity.builder()
                .employee(employee)
                .companyName(dto.getCompanyName())
                .positionTitle(dto.getPositionTitle())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .responsibilities(dto.getResponsibilities())
                .location(dto.getLocation())
                .employmentType(dto.getEmploymentType())
                .supervisorName(dto.getSupervisorName())
                .supervisorContact(dto.getSupervisorContact())
                .reasonForLeaving(dto.getReasonForLeaving())
                .build();

        return mapToDTO(workHistoryRepository.save(workHistory));
    }

    public List<WorkHistoryDTO> getWorkHistoryByEmployeeId(Long employeeId) {
        return workHistoryRepository.findByEmployeeId(employeeId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public void deleteWorkHistory(Long id) {
        workHistoryRepository.deleteById(id);
    }

    private WorkHistoryDTO mapToDTO(WorkHistoryEntity wh) {
        return WorkHistoryDTO.builder()
                .id(wh.getId())
                .employeeId(wh.getEmployee().getId())
                .companyName(wh.getCompanyName())
                .positionTitle(wh.getPositionTitle())
                .startDate(wh.getStartDate())
                .endDate(wh.getEndDate())
                .responsibilities(wh.getResponsibilities())
                .location(wh.getLocation())
                .employmentType(wh.getEmploymentType())
                .supervisorName(wh.getSupervisorName())
                .supervisorContact(wh.getSupervisorContact())
                .reasonForLeaving(wh.getReasonForLeaving())
                .build();
    }

    public WorkHistoryDTO updateWorkHistory(Long id, WorkHistoryDTO dto) {
        WorkHistoryEntity existing = workHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Work history record not found"));

        Employees employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existing.setEmployee(employee);
        existing.setCompanyName(dto.getCompanyName());
        existing.setPositionTitle(dto.getPositionTitle());
        existing.setStartDate(dto.getStartDate());
        existing.setEndDate(dto.getEndDate());
        existing.setResponsibilities(dto.getResponsibilities());
        existing.setLocation(dto.getLocation());
        existing.setEmploymentType(dto.getEmploymentType());
        existing.setSupervisorName(dto.getSupervisorName());
        existing.setSupervisorContact(dto.getSupervisorContact());
        existing.setReasonForLeaving(dto.getReasonForLeaving());

        return mapToDTO(workHistoryRepository.save(existing));
    }

}
