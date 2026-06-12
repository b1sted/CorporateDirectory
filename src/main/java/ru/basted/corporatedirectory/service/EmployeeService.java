package ru.basted.corporatedirectory.service;

import ru.basted.corporatedirectory.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);

    Employee createEmployee(Employee employee);
    Employee changeEmployee(Long id, Employee employee);

    void removeEmployee(Long id);
}
