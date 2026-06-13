package ru.basted.corporatedirectory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private String department;
    private String position;
}
