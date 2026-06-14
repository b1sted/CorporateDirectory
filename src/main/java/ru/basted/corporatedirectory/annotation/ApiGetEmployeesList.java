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
        responseCode = "200",
        description = "Список сотрудников успешно получен",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponseDto.class),
                examples = @ExampleObject(value = """
                        [
                            {
                                "id": 1,
                                "fullName": "Lucia Ernser",
                                "email": "Kayley.Borer@yahoo.com",
                                "department": "Factors",
                                "position": "Legacy Integration Director"
                            },
                            {
                                "id": 2,
                                "fullName": "Teresa Grant",
                                "email": "Everett.Beatty@gmail.com",
                                "department": "Program",
                                "position": "Lead Interactions Executive"
                            },
                            {
                                "id": 3,
                                "fullName": "Lloyd Rempel",
                                "email": "Mitchel.Upton@gmail.com",
                                "department": "Interactions",
                                "position": "Future Mobility Developer"
                            }
                        ]
                        """)
        )
)
public @interface ApiGetEmployeesList {
}
