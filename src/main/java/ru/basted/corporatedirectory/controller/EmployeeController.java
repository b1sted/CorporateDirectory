package ru.basted.corporatedirectory.controller;

import ru.basted.corporatedirectory.dto.EmployeeCreateDto;
import ru.basted.corporatedirectory.dto.EmployeeResponseDto;
import ru.basted.corporatedirectory.service.EmployeeService;

import lombok.AllArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        return ResponseEntity.ok(service.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(
            @PathVariable @Positive(message = "ID не должен быть меньше нуля") Long id
    ) {
        return ResponseEntity.ok(service.getEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(@Valid @RequestBody EmployeeCreateDto createDto) {
        EmployeeResponseDto saved = service.createEmployee(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> changeEmployee(
            @PathVariable @Positive(message = "ID не должен быть меньше нуля") Long id,
            @RequestBody @Valid EmployeeCreateDto createDto
    ) {
        EmployeeResponseDto changed = service.changeEmployee(id, createDto);
        return ResponseEntity.status(HttpStatus.OK).body(changed);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeEmployee(
            @PathVariable @Positive(message = "ID не должен быть меньше нуля") Long id
    ) {
        service.removeEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
