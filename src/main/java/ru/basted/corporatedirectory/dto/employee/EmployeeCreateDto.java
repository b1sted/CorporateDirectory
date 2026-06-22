package ru.basted.corporatedirectory.dto.employee;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "Модель данных для добавления нового сотрудника в базу данных")
public class EmployeeCreateDto {
    @NotBlank(message = "Укажите полное имя сотрудника")
    @Schema(description = "Фамилия и имя сотрудника", example = "Ruth Hoeger")
    private String fullName;

    @NotBlank(message = "Электронная почта обязательна для заполнения")
    @Email(message = "Введен неверный формат email (например, ivan@company.com)")
    @Schema(description = "Электронная почта сотрудника", example = "Ruth.Hoeger@basted.ru")
    private String email;

    @NotBlank(message = "Необходимо указать департамент или отдел")
    @Schema(description = "Департамент/отдел сотрудника", example = "Marketing")
    private String department;

    @NotBlank(message = "Укажите должность сотрудника")
    @Schema(description = "Должность сотрудника", example = "Regional Infrastructure Director")
    private String position;
}
