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
        responseCode = "201",
        description = "Сотрудник успешно добавлен в базу данных",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponseDto.class),
                examples = @ExampleObject(value = """
                        {
                            "id": 1,
                            "fullName": "Ruth Hoeger",
                            "email": "Ruth.Hoeger@basted.ru",
                            "department": "Marketing",
                            "position": "Regional Infrastructure Director"
                        }
                        """)
        )
)
public @interface ApiCreateEmployee {
}
