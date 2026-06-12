package ru.basted.corporatedirectory.controller;

import org.springframework.validation.annotation.Validated;
import ru.basted.corporatedirectory.model.Employee;
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
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(service.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable @Positive(message = "ID не должен быть меньше нуля") Long id
    ) {
        return ResponseEntity.ok(service.getEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee saved = service.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> changeEmployee(
            @PathVariable @Positive(message = "ID не должен быть меньше нуля") Long id,
            @RequestBody @Valid Employee employee
    ) {
        Employee changed = service.changeEmployee(id, employee);
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
