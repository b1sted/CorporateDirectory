package ru.basted.corporatedirectory.swagger;

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
import ru.basted.corporatedirectory.dto.account.AccountResponseDto;

public interface AccountApiDocs {
    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(
            responseCode = "200",
            description = "Список учетных записей пользователей успешно получен",
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
                    schema = @Schema(implementation = AccountResponseDto.class),
                    examples = @ExampleObject(value = """
                            [
                              {
                                "id": 1,
                                "username": "administrator",
                                "role": "ROLE_ADMIN",
                                "createdAt": "2026-06-20T01:40:14"
                              },
                              {
                                "id": 2,
                                "username": "basted",
                                "role": "ROLE_USER",
                                "createdAt": "2026-06-20T01:50:51"
                              }
                            ]
                            """)
            )
    )
    @interface GetList {}

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(
            responseCode = "200",
            description = "Данные о учетной записи пользователя успешно получены",
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
                    schema = @Schema(implementation = AccountResponseDto.class),
                    examples = @ExampleObject(value = """
                            {
                              "id": 1,
                              "username": "administrator",
                              "role": "ROLE_ADMIN",
                              "createdAt": "2026-06-20T01:40:14"
                            }
                            """)
            )
    )
    @interface GetEntity {}

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(
            responseCode = "201",
            description = "Учетная запись успешно создана",
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
                    schema = @Schema(implementation = AccountResponseDto.class),
                    examples = @ExampleObject(value = """
                            {
                              "id": 3,
                              "username": "test",
                              "role": "ROLE_USER",
                              "createdAt": "2026-06-20T01:40:14"
                            }
                            """)
            )
    )
    @interface Register {}

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(
            responseCode = "204",
            description = "Пароль от учетной записи успешно изменен",
            headers = {
                    @Header(name = "Cache-Control", description = "Запрет кэша",
                            schema = @Schema(type = "string", example = "no-cache, no-store, max-age=0, must-revalidate")),
                    @Header(name = "Pragma", description = "Запрет кэша HTTP/1.0",
                            schema = @Schema(type = "string", example = "no-cache")),
                    @Header(name = "X-Frame-Options", description = "Защита от кликджекинга",
                            schema = @Schema(type = "string", example = "DENY"))
            }
    )
    @interface ChangePassword {}

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(
            responseCode = "204",
            description = "Роль учетной записи успешно изменена",
            headers = {
                    @Header(name = "Cache-Control", description = "Запрет кэша",
                            schema = @Schema(type = "string", example = "no-cache, no-store, max-age=0, must-revalidate")),
                    @Header(name = "Pragma", description = "Запрет кэша HTTP/1.0",
                            schema = @Schema(type = "string", example = "no-cache")),
                    @Header(name = "X-Frame-Options", description = "Защита от кликджекинга",
                            schema = @Schema(type = "string", example = "DENY"))
            }
    )
    @interface ChangeRole {}

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(
            responseCode = "204",
            description = "Учетная запись пользователя успешно удалена",
            headers = {
                    @Header(name = "Cache-Control", description = "Запрет кэша",
                            schema = @Schema(type = "string", example = "no-cache, no-store, max-age=0, must-revalidate")),
                    @Header(name = "Pragma", description = "Запрет кэша HTTP/1.0",
                            schema = @Schema(type = "string", example = "no-cache")),
                    @Header(name = "X-Frame-Options", description = "Защита от кликджекинга",
                            schema = @Schema(type = "string", example = "DENY"))
            }
    )
    @interface Delete {}

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(
            responseCode = "409",
            description = "Логин учётной записи уже используется",
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
                              "status": 409,
                              "error": "Conflict",
                              "message": "Имя пользователя занято"
                            }
                            """)
            )
    )
    @interface UsernameAlreadyExists {}

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(
            responseCode = "409",
            description = "Указанная роль уже назначена данному пользователю",
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
                              "status": 409,
                              "error": "Conflict",
                              "message": "Нельзя изменить роль: пользователь уже является ROLE_USER"
                            }
                            """)
            )
    )
    @interface IdenticalRole {}
}
