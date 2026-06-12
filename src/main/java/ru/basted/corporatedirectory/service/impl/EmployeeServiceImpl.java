package ru.basted.corporatedirectory.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import ru.basted.corporatedirectory.exception.ResourceNotFoundException;
import ru.basted.corporatedirectory.model.Employee;
import ru.basted.corporatedirectory.repository.EmployeeRepository;
import ru.basted.corporatedirectory.service.EmployeeService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee changeEmployee(Long id, Employee employee) {
        Employee existingEmployee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        existingEmployee.setFullName(employee.getFullName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setPosition(employee.getPosition());

        return repository.save(existingEmployee);
    }

    @Override
    public void removeEmployee(Long id) {
        repository.deleteById(id);
    }
}
