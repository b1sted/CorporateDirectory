package ru.basted.corporatedirectory.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;
import ru.basted.corporatedirectory.annotation.*;
import ru.basted.corporatedirectory.dto.EmployeeCreateDto;
import ru.basted.corporatedirectory.dto.EmployeeResponseDto;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Сотрудники", description = "Управление базой данных сотрудников")
public interface EmployeeApi {
    @Operation(
            summary = "Получить список всех сотрудников",
            description = "Возвращает список всех действующих сотрудников компании с их должностями и контактными данными."
    )
    @ApiGetEmployeesList
    @GetMapping
    ResponseEntity<List<EmployeeResponseDto>> getAllEmployees();

    @Operation(
            summary = "Получить сотрудника по ID",
            description = "Возвращает информацию о действующем сотруднике по его уникальному идентификатору."
    )
    @ApiGetEmployeeById
    @ApiUserNotFoundException
    @GetMapping("/{id}")
    ResponseEntity<EmployeeResponseDto> getEmployeeById(
            @Parameter(description = "Уникальный идентификатор сотрудника", required = true, example = "1")
            @PathVariable("id")
            @Positive(message = "ID не должен быть меньше нуля") Long id
    );

    @Operation(
            summary = "Регистрация нового сотрудника",
            description = "Создает учетную запись сотрудника в системе и вносит его в список действующих " +
                    "работников компании. Возвращает информацию созданного сотрудника с присвоенным ему " +
                    "уникальным идентификатором."
    )
    @ApiCreateEmployee
    @ApiMethodArgumentNotValid
    @ApiEmailAlreadyExists
    @PostMapping
    ResponseEntity<EmployeeResponseDto> createEmployee(@Valid @RequestBody EmployeeCreateDto createDto);

    @Operation(
            summary = "Обновление данных сотрудника",
            description = "Полностью заменяет текущие данные сотрудника новыми из тела запроса."
    )
    @ApiChangeEmployee
    @ApiValidationError
    @ApiUserNotFoundException
    @ApiEmailAlreadyExists
    @PutMapping("/{id}")
    ResponseEntity<EmployeeResponseDto> changeEmployee(
            @Parameter(description = "Уникальный идентификатор сотрудника", required = true, example = "1")
            @PathVariable("id")
            @Positive(message = "ID не должен быть меньше нуля") Long id,

            @RequestBody
            @Valid EmployeeCreateDto createDto
    );

    @Operation(
            summary = "Удаление сотрудника",
            description = "Удаляет из базы данных сотрудника."
    )
    @ApiRemoveEmployee
    @ApiUserNotFoundException
    @DeleteMapping("/{id}")
    ResponseEntity<Void> removeEmployee(
            @Parameter(description = "Уникальный идентификатор сотрудника", required = true, example = "1")
            @PathVariable("id")
            @Positive(message = "ID не должен быть меньше нуля") Long id
    );
}
