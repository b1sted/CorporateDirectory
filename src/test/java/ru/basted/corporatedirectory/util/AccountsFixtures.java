package ru.basted.corporatedirectory.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ru.basted.corporatedirectory.dto.account.AccountResponseDto;

public class AccountsFixtures {
    public static AccountResponseDto validAccount(Long id, String username, String role, String createdAt) {
        AccountResponseDto responseDto = new AccountResponseDto();

        responseDto.setId(id);
        responseDto.setUsername(username);
        responseDto.setRole(role);
        responseDto.setCreatedAt(LocalDateTime.parse(createdAt));

        return responseDto;
    }

    public static List<AccountResponseDto> validListOfAccounts() {
        List<AccountResponseDto> accounts = new ArrayList<>();

        accounts.add(validAccount(1L, "administrator", "ROLE_ADMIN", "2026-06-20T01:40:14"));
        accounts.add(validAccount(2L, "basted", "ROLE_USER", "2026-06-20T01:50:51"));

        return accounts;
    }
}
