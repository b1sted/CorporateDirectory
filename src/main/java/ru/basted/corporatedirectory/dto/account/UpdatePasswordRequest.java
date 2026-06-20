package ru.basted.corporatedirectory.dto.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель данных для изменения пароля учётной записи пользователя")
public record UpdatePasswordRequest(
        @NotBlank(message = "Пароль не должен быть пустым")
        @Size(min = 8, max = 128, message = "Пароль должен быть от 8 до 128 символов")
        @Schema(description = "Новый пароль", example = "CFQnZwOC51JSmHlV2gRMZ1BtwG8bDIQm") String password
) {
}
