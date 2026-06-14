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
        description = "Ошибка валидации параметров запроса",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponseDto.class),
                examples = @ExampleObject(value = """
                        {
                            "timestamp": "2026-06-14T00:16:03",
                            "status": 400,
                            "error": "Bad Request",
                            "message": "Ошибка валидации параметров запроса",
                            "fieldErrors": {
                                "id": "ID не должен быть меньше нуля"
                            }
                        }
                        """)
        )
)
public @interface ApiHandlerMethodValidationException {
}
