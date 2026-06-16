package ru.basted.corporatedirectory.dto.account;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccountResponseDto {
    private Long id;
    private String username;
    private String role;
    private LocalDateTime createdAt;
}
