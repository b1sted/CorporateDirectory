package ru.basted.corporatedirectory.contract.employees;

import ru.basted.corporatedirectory.dto.EmployeeCreateDto;
import ru.basted.corporatedirectory.exception.EmailAlreadyExistsException;
import ru.basted.corporatedirectory.exception.UserNotFoundException;

import org.junit.jupiter.api.BeforeEach;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class EmployeeErrorBase extends AbstractContractTestBase {
    @BeforeEach
    public void setupErrorMocks() {
        when(service.getEmployeeById(any(Long.class)))
                .thenThrow(new UserNotFoundException("Сотрудник c ID 99 не найден"));

        when(service.createEmployee(any(EmployeeCreateDto.class)))
                .thenThrow(new EmailAlreadyExistsException("Пользователь с таким адресом электронной почты " +
                        "уже существует в базе данных"));

        when(service.changeEmployee(eq(99L), any(EmployeeCreateDto.class)))
                .thenThrow(new UserNotFoundException("Сотрудник c ID 99 не найден"));

        when(service.changeEmployee(eq(1L), any(EmployeeCreateDto.class)))
                .thenThrow(new EmailAlreadyExistsException("Пользователь с таким адресом электронной почты " +
                        "уже существует в базе данных"));

        doThrow(new UserNotFoundException("Сотрудник c ID 99 не найден")).when(service).removeEmployee(any(Long.class));
    }
}
