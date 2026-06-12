package ru.basted.corporatedirectory.service.impl;

import ru.basted.corporatedirectory.exception.EmailAlreadyExistsException;
import ru.basted.corporatedirectory.exception.ResourceNotFoundException;
import ru.basted.corporatedirectory.model.Employee;
import ru.basted.corporatedirectory.repository.EmployeeRepository;
import ru.basted.corporatedirectory.service.EmployeeService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Сотрудник c id " + id + " не найден"));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (repository.existsByEmail(employee.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "Пользователь с email " + employee.getEmail() + " уже существует"
            );
        }

        return repository.save(employee);
    }

    @Override
    public Employee changeEmployee(Long id, Employee employee) {
        Employee existingEmployee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Сотрудник c id " + id + " не найден"));

        String newEmail = employee.getEmail();

        if (!existingEmployee.getEmail().equals(newEmail) && repository.existsByEmail(newEmail)) {
            throw new EmailAlreadyExistsException(
                    "Пользователь с email " + employee.getEmail() + " уже существует"
            );
        }

        existingEmployee.setFullName(employee.getFullName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setPosition(employee.getPosition());

        return repository.save(existingEmployee);
    }

    @Override
    public void removeEmployee(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Сотрудник c id " + id + " не найден");
        }

        repository.deleteById(id);
    }
}
