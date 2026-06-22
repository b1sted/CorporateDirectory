package ru.basted.corporatedirectory.contract.employees;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import ru.basted.corporatedirectory.dto.employee.EmployeeCreateDto;
import ru.basted.corporatedirectory.dto.employee.EmployeeResponseDto;
import ru.basted.corporatedirectory.util.ContractFixtures;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

public abstract class EmployeeSuccessBase extends EmployeeContractBase {
    @BeforeEach
    public void setupSuccessMocks() {
        final EmployeeResponseDto testEmployee = ContractFixtures.validEmployee(1L, "Ruth Hoeger",
                "Ruth.Hoeger@basted.ru", "Marketing", "Regional Infrastructure Director");

        Mockito.when(employeeService.getAllEmployees()).thenReturn(ContractFixtures.validListOfEmployees());
        Mockito.when(employeeService.getEmployeeById(1L)).thenReturn(testEmployee);
        Mockito.when(employeeService.createEmployee(any(EmployeeCreateDto.class))).thenReturn(testEmployee);
        Mockito.when(employeeService.changeEmployee(any(Long.class), any(EmployeeCreateDto.class))).thenReturn(testEmployee);
        doNothing().when(employeeService).removeEmployee(any(Long.class));
    }
}
