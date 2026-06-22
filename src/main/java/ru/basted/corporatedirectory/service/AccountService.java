package ru.basted.corporatedirectory.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.basted.corporatedirectory.dto.account.AccountCreateDto;
import ru.basted.corporatedirectory.dto.account.AccountResponseDto;
import ru.basted.corporatedirectory.model.Role;

import java.util.List;

public interface AccountService extends UserDetailsService {
    List<AccountResponseDto> getAllAccounts();
    AccountResponseDto getAccountById(Long id);

    AccountResponseDto registerNewAccount(AccountCreateDto createDto);

    void changePassword(Long id, String password);
    void changeRole(Long id, String role);

    void deleteAccount(Long id);
}
