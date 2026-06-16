package ru.basted.corporatedirectory.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Модель данных для изменения информации о пользователе")
public class AccountUpdateDto {
    private String password;
    private String role;
}
