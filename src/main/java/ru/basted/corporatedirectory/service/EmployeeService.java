package ru.basted.corporatedirectory.service;

import ru.basted.corporatedirectory.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);

    Employee createEmployee(Employee employee);
    Employee removeEmployee(Employee employee);

    void changeEmployee(Employee employee);
}
