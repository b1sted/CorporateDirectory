package ru.basted.corporatedirectory.contract.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummy-auth")
public class DummyAuthController {
    @GetMapping("/401")
    public void throwUnauthorized() {
        throw new org.springframework.security.authentication.InsufficientAuthenticationException("Неверный токен или токен истек");
    }
}
