package ru.basted.corporatedirectory.repository;

import ru.basted.corporatedirectory.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);
    List<Employee> findAllByOrderByIdAsc();
}
