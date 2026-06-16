package ru.basted.corporatedirectory.mapper;

import ru.basted.corporatedirectory.dto.account.AccountCreateDto;
import ru.basted.corporatedirectory.dto.account.AccountResponseDto;
import ru.basted.corporatedirectory.model.Account;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toEntity(AccountCreateDto dto);
    AccountResponseDto toResponseDto(Account entity);
}
