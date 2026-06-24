package ru.basted.corporatedirectory.service.impl;

import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ru.basted.corporatedirectory.dto.account.AccountCreateDto;
import ru.basted.corporatedirectory.dto.account.AccountResponseDto;
import ru.basted.corporatedirectory.exception.IdenticalRoleException;
import ru.basted.corporatedirectory.exception.UserNotFoundException;
import ru.basted.corporatedirectory.exception.UsernameAlreadyExistsException;
import ru.basted.corporatedirectory.mapper.AccountMapper;
import ru.basted.corporatedirectory.model.Account;
import ru.basted.corporatedirectory.model.Role;
import ru.basted.corporatedirectory.repository.AccountRepository;
import ru.basted.corporatedirectory.service.AccountService;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    private final AccountMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь " + username + " не найден"));

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(account.getRole().name());

        return new User(
                account.getUsername(),
                account.getPassword(),
                Collections.singletonList(authority)
        );
    }

    @Override
    public List<AccountResponseDto> getAllAccounts() {
        return repository.findAllByOrderByIdAsc()
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public AccountResponseDto getAccountById(Long id) {
        Account account = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь c ID " + id + " не найден"));

        return mapper.toResponseDto(account);
    }

    @Override
    public AccountResponseDto registerNewAccount(AccountCreateDto createDto) {
        if (repository.existsByUsername(createDto.getUsername())) {
            throw new UsernameAlreadyExistsException("Имя пользователя занято");
        }

        Account account = mapper.toEntity(createDto);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole(Role.fromString(createDto.getRole()));

        Account savedAccount = repository.save(account);

        return mapper.toResponseDto(savedAccount);
    }

    @Override
    public void changePassword(Long id, String password) {
        Account account = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь c ID " + id + " не найден"));

        account.setPassword(passwordEncoder.encode(password));
        repository.save(account);
    }

    @Override
    public void changeRole(Long id, String role) {
        Account account = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь c ID " + id + " не найден"));

        Role newRole = Role.fromString(role);
        if (account.getRole().equals(newRole)) {
            throw new IdenticalRoleException("Нельзя изменить роль: пользователь уже является " + newRole);
        }

        account.setRole(newRole);
        repository.save(account);
    }

    @Override
    public void deleteAccount(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException("Пользователь c ID " + id + " не найден");
        }

        repository.deleteById(id);
    }
}
