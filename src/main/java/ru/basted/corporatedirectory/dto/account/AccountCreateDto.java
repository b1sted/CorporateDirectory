package ru.basted.corporatedirectory.dto.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import ru.basted.corporatedirectory.model.Role;

@Getter
@Setter
@Schema(description = "Модель данных для добавления нового пользователя в базу данных")
public class AccountCreateDto {
    @NotBlank(message = "Имя пользователя не должно быть пустым")
    @Size(min = 4, max = 64, message = "Имя пользователя должно быть от 4 до 64 символов")
    @Schema(description = "Имя пользователя", example = "basted")
    private String username;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 8, max = 128, message = "Пароль должен быть от 8 до 128 символов")
    @Schema(description = "Пароль пользователя", example = "dm89jULndiFNt5zaZL0Ub4tu5IqTDEoH")
    private String password;

    @NotNull(message = "Роль не должна быть пустой")
    @Schema(description = "Роль пользователя", example = "user")
    private Role role;
}
