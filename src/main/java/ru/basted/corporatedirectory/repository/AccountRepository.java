package ru.basted.corporatedirectory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.basted.corporatedirectory.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    List<Account> findAllByOrderByIdAsc();
    boolean existsByUsername(String username);
}
