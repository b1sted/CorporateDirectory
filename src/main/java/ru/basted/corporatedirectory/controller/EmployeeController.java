package ru.basted.corporatedirectory.controller;

import ru.basted.corporatedirectory.api.EmployeeApi;
import ru.basted.corporatedirectory.dto.employee.EmployeeCreateDto;
import ru.basted.corporatedirectory.dto.employee.EmployeeResponseDto;
import ru.basted.corporatedirectory.service.EmployeeService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
public class EmployeeController implements EmployeeApi {
    private final EmployeeService service;

    @Override
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        return ResponseEntity.ok(service.getAllEmployees());
    }

    @Override
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(Long id) {
        return ResponseEntity.ok(service.getEmployeeById(id));
    }

    @Override
    public ResponseEntity<EmployeeResponseDto> createEmployee(EmployeeCreateDto createDto) {
        EmployeeResponseDto saved = service.createEmployee(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Override
    public ResponseEntity<EmployeeResponseDto> changeEmployee(Long id, EmployeeCreateDto createDto) {
        EmployeeResponseDto changed = service.changeEmployee(id, createDto);
        return ResponseEntity.status(HttpStatus.OK).body(changed);
    }

    @Override
    public ResponseEntity<Void> removeEmployee(Long id) {
        service.removeEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
