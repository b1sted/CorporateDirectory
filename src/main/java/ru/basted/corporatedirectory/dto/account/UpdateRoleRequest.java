package ru.basted.corporatedirectory.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;

import ru.basted.corporatedirectory.model.Role;

@Schema(description = "Модель данных для замены роли учётной записи")
public record UpdateRoleRequest(
        @Schema(description = "Новая роль") Role role
) {
}
