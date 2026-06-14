package ru.basted.corporatedirectory.annotation;

import ru.basted.corporatedirectory.dto.ErrorResponseDto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(
        responseCode = "400",
        description = "Неверный запрос (ошибка валидации входных данных/параметров запроса)",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponseDto.class),
                examples = {
                        @ExampleObject(
                                name = "1. Ошибка валидации тела запроса",
                                description = "&nbsp;",
                                value = """
                            {
                                "timestamp": "2026-06-14T00:10:06",
                                "status": 400,
                                "error": "Bad Request",
                                "message": "Ошибка валидации данных",
                                "fieldErrors": {
                                    "fullName": "Укажите полное имя сотрудника",
                                    "email": "Электронная почта обязательна для заполнения"
                                }
                            }
                            """),
                        @ExampleObject(
                                name = "2. Ошибка валидации параметра URL",
                                description = "&nbsp;",
                                value = """
                            {
                                "timestamp": "2026-06-14T00:16:03",
                                "status": 400,
                                "error": "Bad Request",
                                "message": "Ошибка валидации параметров запроса",
                                "fieldErrors": {
                                    "id": "ID не должен быть меньше нуля"
                                }
                            }
                            """),
                }
        )
)
public @interface ApiValidationError {
}