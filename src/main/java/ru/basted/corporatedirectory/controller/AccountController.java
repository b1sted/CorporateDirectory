package ru.basted.corporatedirectory.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.basted.corporatedirectory.api.AccountApi;
import ru.basted.corporatedirectory.dto.account.AccountCreateDto;
import ru.basted.corporatedirectory.dto.account.AccountResponseDto;
import ru.basted.corporatedirectory.dto.account.UpdatePasswordRequest;
import ru.basted.corporatedirectory.dto.account.UpdateRoleRequest;
import ru.basted.corporatedirectory.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@AllArgsConstructor
public class AccountController implements AccountApi {
    private final AccountService service;

    @Override
    public ResponseEntity<List<AccountResponseDto>> getAllAccounts() {
        return ResponseEntity.ok(service.getAllAccounts());
    }

    @Override
    public ResponseEntity<AccountResponseDto> getAccountById(Long id) {
        return ResponseEntity.ok(service.getAccountById(id));
    }

    @Override
    public ResponseEntity<AccountResponseDto> registerNewAccount(AccountCreateDto createDto) {
        AccountResponseDto saved = service.registerNewAccount(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Override
    public ResponseEntity<Void> changePassword(Long id, UpdatePasswordRequest request) {
        service.changePassword(id, request.password());
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> changeRole(Long id, UpdateRoleRequest request) {
        service.changeRole(id, request.role());
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteAccount(Long id) {
        service.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
