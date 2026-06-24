package ru.basted.corporatedirectory.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Модель данных, возвращаемая при создании или получении информации о учётной записи сотрудника")
public class EmployeeResponseDto {
    @Schema(description = "Уникальный идентификатор сотрудника", example = "1")
    private Long id;

    @Schema(description = "Имя и фамилия сотрудника", example = "Ruth Hoeger")
    private String fullName;

    @Schema(description = "Адрес внутренней электронной почты сотрудника", example = "Ruth.Hoeger@basted.ru")
    private String email;

    @Schema(description = "Департамент/отдел сотрудника", example = "Program")
    private String department;

    @Schema(description = "Должность сотрудника", example = "Lead Interactions Executive")
    private String position;
}
