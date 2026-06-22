package ru.basted.corporatedirectory.contract.auth;

import org.junit.jupiter.api.BeforeEach;

import ru.basted.corporatedirectory.contract.CoreContractBase;

public abstract class AuthErrorBase extends CoreContractBase {
    @BeforeEach
    public void setup() {
        setupMockMvcForControllers(new DummyAuthController());
    }
}
