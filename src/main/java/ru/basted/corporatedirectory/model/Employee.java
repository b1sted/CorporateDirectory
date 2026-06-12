package ru.basted.corporatedirectory.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Укажите полное имя сотрудника")
    private String fullName;

    @NotBlank(message = "Электронная почта обязательна для заполнения")
    @Email(message = "Введен неверный формат email (например, ivan@company.com)")
    private String email;

    @NotBlank(message = "Необходимо указать департамент или отдел")
    private String department;

    @NotBlank(message = "Укажите должность сотрудника")
    private String position;
}

