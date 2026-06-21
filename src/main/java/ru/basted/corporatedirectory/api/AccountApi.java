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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ru.basted.corporatedirectory.dto.account.AccountCreateDto;
import ru.basted.corporatedirectory.dto.account.AccountResponseDto;
import ru.basted.corporatedirectory.dto.account.UpdatePasswordRequest;
import ru.basted.corporatedirectory.dto.account.UpdateRoleRequest;
import ru.basted.corporatedirectory.swagger.AccountApiDocs;
import ru.basted.corporatedirectory.swagger.global.ApiErrors;

@Tag(name = "Пользователи", description = "Управление базой данных пользователей")
public interface AccountApi {
    @Operation(
            summary = "Получить список зарегистрированных пользователей",
            description = "Возвращает список всех зарегистрированных пользователей с информацией о них. " +
                    "Доступна только администраторам."
    )
    @AccountApiDocs.GetList
    @ApiErrors.NotFoundOrHidden
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    ResponseEntity<List<AccountResponseDto>> getAllAccounts();

    @Operation(
            summary = "Получить пользователя по ID",
            description = "Возвращает информацию о действующем пользователе по его уникальному идентификатору. " +
                    "Доступна только администраторам и самому пользователю."
    )
    @AccountApiDocs.GetEntity
    @ApiErrors.ResourceNotFound
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
    @AccountApiDocs.Register
    @ApiErrors.NotFoundOrHidden
    @AccountApiDocs.InvalidArgumentsUponRegistration
    @AccountApiDocs.UsernameAlreadyExists
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<AccountResponseDto> registerNewAccount(@Valid @RequestBody AccountCreateDto createDto);

    @Operation(
            summary = "Смена пароля пользователя",
            description = "Изменяет пароль действующего пользователя. " +
                    "Доступна только администраторам и самому пользователю."
    )
    @AccountApiDocs.ChangePassword
    @AccountApiDocs.InvalidPassword
    @ApiErrors.ResourceNotFound
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
    @AccountApiDocs.ChangeRole
    @AccountApiDocs.InvalidRole
    @ApiErrors.ResourceNotFound
    @AccountApiDocs.IdenticalRole
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
    @AccountApiDocs.Delete
    @ApiErrors.ResourceNotFound
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Void> deleteAccount(
            @Parameter(description = "Уникальный идентификатор пользователя", required = true, example = "1")
            @PathVariable
            @Positive(message = "ID не должен быть меньше нуля") Long id
    );
}
