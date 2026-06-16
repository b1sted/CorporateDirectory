package ru.basted.corporatedirectory.service;

import ru.basted.corporatedirectory.dto.employee.EmployeeCreateDto;
import ru.basted.corporatedirectory.dto.employee.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDto> getAllEmployees();
    EmployeeResponseDto getEmployeeById(Long id);

    EmployeeResponseDto createEmployee(EmployeeCreateDto createDto);
    EmployeeResponseDto changeEmployee(Long id, EmployeeCreateDto createDto);

    void removeEmployee(Long id);
}
