package ru.basted.corporatedirectory.contract.accounts;

import org.junit.jupiter.api.BeforeEach;

import ru.basted.corporatedirectory.dto.account.AccountCreateDto;
import ru.basted.corporatedirectory.exception.IdenticalRoleException;
import ru.basted.corporatedirectory.exception.UserNotFoundException;
import ru.basted.corporatedirectory.exception.UsernameAlreadyExistsException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public abstract class AccountErrorBase extends AccountContractBase {
    @BeforeEach
    public void setupErrorMocks() {
        when(accountService.getAccountById(any(Long.class)))
                .thenThrow(new UserNotFoundException("Пользователь c ID 99 не найден"));

        when(accountService.registerNewAccount(any(AccountCreateDto.class)))
                .thenThrow(new UsernameAlreadyExistsException("Имя пользователя занято"));

        doThrow(new IdenticalRoleException("Нельзя изменить роль: пользователь уже является ROLE_USER"))
                .when(accountService).changeRole(any(Long.class), any(String.class));
    }
}
