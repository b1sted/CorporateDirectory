package ru.basted.corporatedirectory.util;

import ru.basted.corporatedirectory.dto.employee.EmployeeResponseDto;

import java.util.ArrayList;
import java.util.List;

public class ContractFixtures {
    public static EmployeeResponseDto validEmployee(Long id, String fullName, String email,
                                                    String department, String position) {
        EmployeeResponseDto responseDto = new EmployeeResponseDto();

        responseDto.setId(id);
        responseDto.setFullName(fullName);
        responseDto.setEmail(email);
        responseDto.setDepartment(department);
        responseDto.setPosition(position);

        return responseDto;
    }

    public static List<EmployeeResponseDto> validListOfEmployees() {
        List<EmployeeResponseDto> employees = new ArrayList<>();

        employees.add(validEmployee(1L, "Lucia Ernser", "Kayley.Borer@yahoo.com",
                "Factors", "Legacy Integration Director"));
        employees.add(validEmployee(2L, "Teresa Grant", "Everett.Beatty@gmail.com",
                "Program", "Lead Interactions Executive"));
        employees.add(validEmployee(3L, "Lloyd Rempel", "Mitchel.Upton@gmail.com",
                "Interactions", "Future Mobility Developer"));

        return employees;
    }
}
