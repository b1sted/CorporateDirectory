package ru.basted.corporatedirectory.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Schema(description = "Структура ответа при ошибке")
public class ErrorResponseDto {
    @Schema(description = "Время возникновения ошибки", example = "2023-10-25T10:15:46Z")
    private LocalDateTime timestamp;

    @Schema(description = "HTTP статус", example = "400")
    private int status;

    @Schema(description = "Тип ошибки", example = "Bad Request")
    private String error;

    @Schema(description = "Общее сообщение", example = "Ошибка валидации входных данных")
    private String message;

    @Schema(description = "Список ошибок по полям", example = "{\"email\": \"Введен неверный формат email\", \"department\": \"Необходимо указать департамент\"}")
    private Map<String, String> fieldErrors;

    @Schema(description = "Путь без Endpoint", example = "/api/v1/accounts/5/role")
    private String path;
}
