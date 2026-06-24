package ru.basted.corporatedirectory.api;

import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ru.basted.corporatedirectory.dto.employee.EmployeeCreateDto;
import ru.basted.corporatedirectory.dto.employee.EmployeeResponseDto;
import ru.basted.corporatedirectory.swagger.EmployeeApiDocs;
import ru.basted.corporatedirectory.swagger.global.ApiErrors;

@Tag(name = "Сотрудники", description = "Управление базой данных сотрудников")
public interface EmployeeApi {
    @Operation(
            summary = "Получить список всех сотрудников",
            description = "Возвращает список всех действующих сотрудников компании с их должностями и контактными данными."
    )
    @EmployeeApiDocs.GetList
    @ApiErrors.NotFoundOrHidden
    @GetMapping
    ResponseEntity<List<EmployeeResponseDto>> getAllEmployees();

    @Operation(
            summary = "Получить сотрудника по ID",
            description = "Возвращает информацию о действующем сотруднике по его уникальному идентификатору."
    )
    @EmployeeApiDocs.GetEntity
    @ApiErrors.ResourceNotFound
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
    @EmployeeApiDocs.Create
    @EmployeeApiDocs.InvalidCreateDto
    @ApiErrors.NotFoundOrHidden
    @EmployeeApiDocs.EmailConflict
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    ResponseEntity<EmployeeResponseDto> createEmployee(@Valid @RequestBody EmployeeCreateDto createDto);

    @Operation(
            summary = "Обновление данных сотрудника",
            description = "Полностью заменяет текущие данные сотрудника новыми из тела запроса."
    )
    @EmployeeApiDocs.Change
    @EmployeeApiDocs.InvalidCreateDto
    @ApiErrors.ResourceNotFound
    @EmployeeApiDocs.EmailConflict
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
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
    @EmployeeApiDocs.Delete
    @ApiErrors.ResourceNotFound
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    ResponseEntity<Void> removeEmployee(
            @Parameter(description = "Уникальный идентификатор сотрудника", required = true, example = "1")
            @PathVariable("id")
            @Positive(message = "ID не должен быть меньше нуля") Long id
    );
}
