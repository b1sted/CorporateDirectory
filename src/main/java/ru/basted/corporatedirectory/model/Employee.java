package ru.basted.corporatedirectory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Имя сотрудника не должно быть пустым")
    private String fullName;
    @Column(unique = true)
    @Email(message = "Некорректный формат адреса электронной почты")
    private String email;
    private String department;
    private String position;
}
