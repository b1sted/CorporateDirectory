package ru.basted.corporatedirectory.initializer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.basted.corporatedirectory.model.Account;
import ru.basted.corporatedirectory.model.Role;
import ru.basted.corporatedirectory.repository.AccountRepository;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${SPRING_SECURITY_USER_NAME}")
    private String adminUsername;

    @Value("${SPRING_SECURITY_USER_PASSWORD}")
    private String adminPassword;

    @Override
    public void run(String... args) {
        if (!accountRepository.existsByUsername(adminUsername)) {
            Account admin = new Account();

            admin.setUsername(adminUsername);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole(Role.ROLE_ADMIN);

            accountRepository.save(admin);
            System.out.println("Администратор по умолчанию успешно создан.");
        }
    }
}
