package ru.basted.corporatedirectory.mapper;

import org.mapstruct.Mapper;

import ru.basted.corporatedirectory.dto.account.AccountCreateDto;
import ru.basted.corporatedirectory.dto.account.AccountResponseDto;
import ru.basted.corporatedirectory.model.Account;
import ru.basted.corporatedirectory.model.Role;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toEntity(AccountCreateDto dto);
    AccountResponseDto toResponseDto(Account entity);

    default Role mapStringToRole(String roleStr) {
        return Role.fromString(roleStr);
    }
}
