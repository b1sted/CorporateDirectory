package ru.basted.corporatedirectory.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String fullName;
    @Column(unique = true)
    private String email;
    private String department;
    private String position;
}
