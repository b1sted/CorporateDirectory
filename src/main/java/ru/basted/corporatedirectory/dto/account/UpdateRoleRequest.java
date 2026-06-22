package ru.basted.corporatedirectory.dto.account;

import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;

import ru.basted.corporatedirectory.model.Role;

@Schema(description = "Модель данных для замены роли учётной записи")
public record UpdateRoleRequest(
        @NotBlank(message = "Роль не должна быть пустой")
        @Schema(description = "Новая роль", example = "user") String role
) {
}
