package ru.basted.corporatedirectory.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.basted.corporatedirectory.dto.account.AccountCreateDto;
import ru.basted.corporatedirectory.dto.account.AccountResponseDto;
import ru.basted.corporatedirectory.dto.account.UpdatePasswordRequest;
import ru.basted.corporatedirectory.dto.account.UpdateRoleRequest;

import java.util.List;

@Tag(name = "Пользователи", description = "Управление базой данных пользователей")
public interface AccountApi {
    @Operation(
            summary = "Получить список зарегистрированных пользователей",
            description = "Возвращает список всех зарегистрированных пользователей с информацией о них. " +
                    "Доступна только администраторам."
    )
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    ResponseEntity<List<AccountResponseDto>> getAllAccounts();

    @Operation(
            summary = "Получить пользователя по ID",
            description = "Возвращает информацию о действующем пользователе по его уникальному идентификатору. " +
                    "Доступна только администраторам и самому пользователю."
    )
    @GetMapping("/{id}")
    @PreAuthorize("@securityCheck.isOwnerOrAdmin(#id)")
    ResponseEntity<AccountResponseDto> getAccountById(
            @Parameter(description = "Уникальный идентификатор пользователя", required = true, example = "1")
            @PathVariable("id")
            @Positive(message = "ID не должен быть меньше нуля") Long id
    );

    @Operation(
            summary = "Зарегистировать нового пользователя в базе данных",
            description = "При успешной регистрации, возвращает информацию о зарегистированном пользователе. " +
                    "Доступна только администраторам."
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<AccountResponseDto> registerNewAccount(@Valid @RequestBody AccountCreateDto createDto);

    @Operation(
            summary = "Смена пароля пользователя",
            description = "Изменяет пароль действующего пользователя. " +
                    "Доступна только администраторам и самому пользователю."
    )
    @PatchMapping("/{id}/password")
    @PreAuthorize("@securityCheck.isOwnerOrAdmin(#id)")
    ResponseEntity<Void> changePassword(
            @Parameter(description = "Уникальный идентификатор пользователя", required = true, example = "1")
            @PathVariable
            @Positive(message = "ID не должен быть меньше нуля") Long id,

            @Valid @RequestBody UpdatePasswordRequest request
    );

    @Operation(
            summary = "Смена роли пользователя",
            description = "Изменяет роль действующего пользователя. Доступна только администраторам."
    )
    @PatchMapping("/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Void> changeRole(
            @Parameter(description = "Уникальный идентификатор пользователя", required = true, example = "1")
            @PathVariable
            @Positive(message = "ID не должен быть меньше нуля") Long id,

            @Valid @RequestBody UpdateRoleRequest request
    );

    @Operation(
            summary = "Удаление пользователя",
            description = "Удаляет действующего пользователя из базы данных. Доступна только администраторам."
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Void> deleteAccount(
            @Parameter(description = "Уникальный идентификатор пользователя", required = true, example = "1")
            @PathVariable
            @Positive(message = "ID не должен быть меньше нуля") Long id
    );
}
