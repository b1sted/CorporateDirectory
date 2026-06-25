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
import ru.basted.corporatedirectory.dto.employee.EmployeeResponseDto;

public interface EmployeeApiDocs {
    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(
            responseCode = "200",
            description = "Список сотрудников успешно получен",
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
                    schema = @Schema(implementation = EmployeeResponseDto.class),
                    examples = @ExampleObject(value = """
                            [
                              {
                                "id": 1,
                                "fullName": "Ruth Hoeger",
                                "email": "Ruth.Hoeger@basted.ru",
                                "department": "Marketing",
                                "position": "Regional Infrastructure Director"
                              },
                              {
                                "id": 2,
                                "fullName": "Teresa Grant",
                                "email": "Everett.Beatty@gmail.com",
                                "department": "Program",
                                "position": "Lead Interactions Executive"
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
            description = "Информация о сотруднике успешно получена",
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
                    schema = @Schema(implementation = EmployeeResponseDto.class),
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
    @interface GetEntity {}

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(
            responseCode = "201",
            description = "Учётная запись сотрудника успешно создана",
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
                    schema = @Schema(implementation = EmployeeResponseDto.class),
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
    @interface Create {}

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(
            responseCode = "200",
            description = "Данные сотрудника успешно обновлены",
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
                    schema = @Schema(implementation = EmployeeResponseDto.class),
                    examples = @ExampleObject(value = """
                            {
                              "id": 1,
                              "fullName": "Ruth Hoeger",
                              "email": "Ruth.Hoeger@basted.ru",
                              "department": "Program",
                              "position": "Lead Interactions Executive"
                            }
                            """)
            )
    )
    @interface Change {}

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(
            responseCode = "204",
            description = "Учётная запись сотрудника успешно удалена из системы",
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
            responseCode = "400",
            description = "Ошибка валидации данных. Переданы некорректные аргументы в теле запроса",
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
                              "status": 400,
                              "error": "Bad Request",
                              "message": "Ошибка валидации параметров запроса",
                              "fieldErrors": {
                                "fullName": "Укажите полное имя сотрудника",
                                "position": "Укажите должность сотрудника",
                                "department": "Необходимо указать департамент или отдел",
                                "email": "Электронная почта обязательна для заполнения"
                              }
                            }
                            """)
            )
    )
    @interface InvalidCreateDto {}

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(
            responseCode = "409",
            description = "Адрес электронной почты зарезевирован за другим сотрудником",
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
                              "message": "Адрес электронной почты зарезевирован за другим сотрудником"
                            }
                            """)
            )
    )
    @interface EmailConflict {}
}
