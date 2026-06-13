package ru.basted.corporatedirectory.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class EmployeeCreateDto {
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
