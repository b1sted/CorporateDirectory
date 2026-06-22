package ru.basted.corporatedirectory.contract.employees;

import org.junit.jupiter.api.BeforeEach;

import ru.basted.corporatedirectory.dto.employee.EmployeeCreateDto;
import ru.basted.corporatedirectory.exception.EmailAlreadyExistsException;
import ru.basted.corporatedirectory.exception.UserNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public abstract class EmployeeErrorBase extends EmployeeContractBase {
    @BeforeEach
    public void setupErrorMocks() {
        when(employeeService.getEmployeeById(any(Long.class)))
                .thenThrow(new UserNotFoundException("Сотрудник c ID 99 не найден"));

        when(employeeService.createEmployee(any(EmployeeCreateDto.class)))
                .thenThrow(new EmailAlreadyExistsException("Пользователь с таким адресом электронной почты " +
                        "уже существует в базе данных"));

        when(employeeService.changeEmployee(eq(99L), any(EmployeeCreateDto.class)))
                .thenThrow(new UserNotFoundException("Сотрудник c ID 99 не найден"));

        when(employeeService.changeEmployee(eq(1L), any(EmployeeCreateDto.class)))
                .thenThrow(new EmailAlreadyExistsException("Пользователь с таким адресом электронной почты " +
                        "уже существует в базе данных"));

        doThrow(new UserNotFoundException("Сотрудник c ID 99 не найден")).when(employeeService).removeEmployee(any(Long.class));
    }
}
