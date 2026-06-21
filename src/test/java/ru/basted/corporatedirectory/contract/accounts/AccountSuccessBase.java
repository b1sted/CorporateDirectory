package ru.basted.corporatedirectory.contract.accounts;

import org.junit.jupiter.api.BeforeEach;

import ru.basted.corporatedirectory.dto.account.AccountCreateDto;
import ru.basted.corporatedirectory.dto.account.AccountResponseDto;
import ru.basted.corporatedirectory.model.Role;
import ru.basted.corporatedirectory.util.AccountsFixtures;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public abstract class AccountSuccessBase extends AccountContractBase {
    @BeforeEach
    public void setupSuccessMocks() {
        final AccountResponseDto validAccount =
                AccountsFixtures.validAccount(1L, "administrator", "ROLE_ADMIN", "2026-06-20T01:40:14");

        when(accountService.getAllAccounts()).thenReturn(AccountsFixtures.validListOfAccounts());
        when(accountService.getAccountById(any(Long.class))).thenReturn(validAccount);
        when(accountService.registerNewAccount(any(AccountCreateDto.class))).thenReturn(validAccount);
        doNothing().when(accountService).changePassword(any(Long.class), any(String.class));
        doNothing().when(accountService).changeRole(any(Long.class), any(Role.class));
        doNothing().when(accountService).deleteAccount(any(Long.class));
    }
}
