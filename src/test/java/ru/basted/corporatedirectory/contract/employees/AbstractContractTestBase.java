package ru.basted.corporatedirectory.contract.employees;

import ru.basted.corporatedirectory.controller.EmployeeController;
import ru.basted.corporatedirectory.handler.GlobalExceptionHandler;
import ru.basted.corporatedirectory.service.EmployeeService;

import org.junit.jupiter.api.Tag;

import io.restassured.config.LogConfig;
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig;
import org.springframework.test.web.servlet.MockMvc;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@Tag("contract")
@WebMvcTest({EmployeeController.class, GlobalExceptionHandler.class})
public abstract class AbstractContractTestBase {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    protected EmployeeService service;

    @BeforeEach
    public void setupInfrastructure() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        RestAssuredMockMvc.config = RestAssuredMockMvcConfig.config()
                .logConfig(LogConfig.logConfig()
                        .enableLoggingOfRequestAndResponseIfValidationFails());
    }
}
