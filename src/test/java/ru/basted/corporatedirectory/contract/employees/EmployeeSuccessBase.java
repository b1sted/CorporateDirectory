package ru.basted.corporatedirectory.contract.employees;

import ru.basted.corporatedirectory.dto.employee.EmployeeCreateDto;
import ru.basted.corporatedirectory.dto.employee.EmployeeResponseDto;
import ru.basted.corporatedirectory.util.ContractFixtures;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

public abstract class EmployeeSuccessBase extends AbstractContractTestBase {
    @BeforeEach
    public void setupSuccessMocks() {
        final EmployeeResponseDto testEmployee = ContractFixtures.validEmployee(1L, "Ruth Hoeger",
                "Ruth.Hoeger@basted.ru", "Marketing", "Regional Infrastructure Director");

        Mockito.when(service.getAllEmployees()).thenReturn(ContractFixtures.validListOfEmployees());
        Mockito.when(service.getEmployeeById(1L)).thenReturn(testEmployee);
        Mockito.when(service.createEmployee(any(EmployeeCreateDto.class))).thenReturn(testEmployee);
        Mockito.when(service.changeEmployee(any(Long.class), any(EmployeeCreateDto.class))).thenReturn(testEmployee);
        doNothing().when(service).removeEmployee(any(Long.class));
    }
}
