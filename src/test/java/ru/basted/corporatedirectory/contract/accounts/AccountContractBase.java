package ru.basted.corporatedirectory.contract.accounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import ru.basted.corporatedirectory.contract.CoreContractBase;
import ru.basted.corporatedirectory.controller.AccountController;
import ru.basted.corporatedirectory.service.AccountService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public abstract class AccountContractBase extends CoreContractBase {
    @Mock
    protected AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setup() {
        setupMockMvcForControllers(accountController);
    }
}
