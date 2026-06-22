package ru.basted.corporatedirectory.contract.employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import ru.basted.corporatedirectory.contract.CoreContractBase;
import ru.basted.corporatedirectory.controller.EmployeeController;
import ru.basted.corporatedirectory.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public abstract class EmployeeContractBase extends CoreContractBase {
    @Mock
    protected EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setup() {
        setupMockMvcForControllers(employeeController);
    }
}
