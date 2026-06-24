package ru.basted.corporatedirectory.service;

import java.util.List;

import ru.basted.corporatedirectory.dto.employee.EmployeeCreateDto;
import ru.basted.corporatedirectory.dto.employee.EmployeeResponseDto;

public interface EmployeeService {
    List<EmployeeResponseDto> getAllEmployees();
    EmployeeResponseDto getEmployeeById(Long id);

    EmployeeResponseDto createEmployee(EmployeeCreateDto createDto);
    EmployeeResponseDto changeEmployee(Long id, EmployeeCreateDto createDto);

    void removeEmployee(Long id);
}
