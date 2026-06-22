package ru.basted.corporatedirectory.swagger.global;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import ru.basted.corporatedirectory.dto.ErrorResponseDto;

public interface ApiErrors {
    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(
            responseCode = "404",
            description = "Ресурс не найден, либо у вас нет прав на его просмотр",
            headers = {
                    @Header(name = "Cache-Control", description = "Запрет кэша",
                            schema = @Schema(type = "string", example = "no-cache, no-store, max-age=0, must-revalidate")),
                    @Header(name = "Pragma", description = "Запрет кэша HTTP/1.0",
                            schema = @Schema(type = "string", example = "no-cache")),
                    @Header(name = "X-Frame-Options", description = "Защита от кликджекинга",
                            schema = @Schema(type = "string", example = "DENY"))
            },
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class),
                    examples = @ExampleObject(value = """
                            {
                              "timestamp": "2026-06-20T01:40:14",
                              "status": 404,
                              "error": "Not Found",
                              "path": "/api/v1/..."
                            }
                            """)
            )
    )
    @interface NotFoundOrHidden {}

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(
            responseCode = "404",
            description = "Ресурс не найден, либо у вас нет прав на его просмотр",
            headers = {
                    @Header(name = "Cache-Control", description = "Запрет кэша",
                            schema = @Schema(type = "string", example = "no-cache, no-store, max-age=0, must-revalidate")),
                    @Header(name = "Pragma", description = "Запрет кэша HTTP/1.0",
                            schema = @Schema(type = "string", example = "no-cache")),
                    @Header(name = "X-Frame-Options", description = "Защита от кликджекинга",
                            schema = @Schema(type = "string", example = "DENY"))
            },
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class),
                    examples = {
                            @ExampleObject(
                                    name = "Ресурс скрыт из-за недостаточных прав",
                                    description = "&nbsp;",
                                    value = """
                                            {
                                              "timestamp": "2026-06-20T01:40:14",
                                              "status": 404,
                                              "error": "Not Found",
                                              "path": "/api/v1/..."
                                            }
                                            """
                            ),
                            @ExampleObject(
                                    name = "Ресурс не найден",
                                    description = "&nbsp;",
                                    value = """
                                            {
                                              "timestamp": "2026-06-20T01:40:14",
                                              "status": 404,
                                              "error": "Not Found",
                                              "message": "Пользователь c ID 99 не найден"
                                            }
                                            """
                            )
                    }
            )
    )
    @interface ResourceNotFound {}
}
