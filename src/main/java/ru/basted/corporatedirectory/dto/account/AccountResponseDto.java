package ru.basted.corporatedirectory.dto.account;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Модель данных, возвращаемая при создании или получении информации о учётной записи пользователя")
public class AccountResponseDto {
    @Schema(description = "Уникальный идентификатор учётной записи пользователя", example = "1")
    private Long id;

    @Schema(description = "Логин учётной записи пользователя", example = "basted")
    private String username;

    @Schema(description = "Роль, присвоенная учётной записи", example = "ROLE_USER")
    private String role;

    @Schema(description = "Время создания учётной записи", example = "2026-06-20T01:40:14Zl")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
}
