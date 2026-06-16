package ru.basted.corporatedirectory.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.basted.corporatedirectory.model.Account;
import ru.basted.corporatedirectory.repository.AccountRepository;

@Component("securityCheck")
@RequiredArgsConstructor
public class SecurityCheckHelper {
    private final AccountRepository repository;

    public boolean isOwnerOrAdmin(Long targetAccountId) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return true;
        }

        Account targetAccount = repository.findById(targetAccountId).orElse(null);
        if (targetAccount == null) {
            return false;
        }

        return targetAccount.getUsername().equals(currentUsername);
    }
}
