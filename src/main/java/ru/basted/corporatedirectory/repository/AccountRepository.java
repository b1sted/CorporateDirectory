package ru.basted.corporatedirectory.repository;

import ru.basted.corporatedirectory.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    List<Account> findAllByOrderByIdAsc();
    boolean existsByUsername(String username);
}
