package ru.basted.corporatedirectory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.basted.corporatedirectory.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);
    List<Employee> findAllByOrderByIdAsc();
}
