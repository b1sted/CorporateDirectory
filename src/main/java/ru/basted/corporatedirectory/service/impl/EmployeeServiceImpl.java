package ru.basted.corporatedirectory.service.impl;

import ru.basted.corporatedirectory.dto.EmployeeCreateDto;
import ru.basted.corporatedirectory.dto.EmployeeResponseDto;
import ru.basted.corporatedirectory.exception.EmailAlreadyExistsException;
import ru.basted.corporatedirectory.exception.UserNotFoundException;
import ru.basted.corporatedirectory.mapper.EmployeeMapper;
import ru.basted.corporatedirectory.model.Employee;
import ru.basted.corporatedirectory.repository.EmployeeRepository;
import ru.basted.corporatedirectory.service.EmployeeService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return repository.findAllByOrderByIdAsc()
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Сотрудник c ID " + id + " не найден"));

        return mapper.toResponseDto(employee);
    }

    @Override
    public EmployeeResponseDto createEmployee(EmployeeCreateDto createDto) {
        if (repository.existsByEmail(createDto.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "Пользователь с таким адресом электронной почты уже существует в базе данных"
            );
        }

        Employee employee = mapper.toEntity(createDto);
        Employee savedEmployee = repository.save(employee);
        return mapper.toResponseDto(savedEmployee);
    }

    @Override
    @Transactional
    public EmployeeResponseDto changeEmployee(Long id, EmployeeCreateDto createDto) {
        Employee existingEmployee = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Сотрудник c ID " + id + " не найден"));

        String newEmail = createDto.getEmail();
        if (!existingEmployee.getEmail().equals(newEmail) && repository.existsByEmail(newEmail)) {
            throw new EmailAlreadyExistsException(
                    "Пользователь с таким адресом электронной почты уже существует в базе данных"
            );
        }

        mapper.updateEntityFromDto(createDto, existingEmployee);

        Employee savedEmployee = repository.save(existingEmployee);
        return mapper.toResponseDto(savedEmployee);
    }

    @Override
    public void removeEmployee(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException("Сотрудник c ID " + id + " не найден");
        }

        repository.deleteById(id);
    }
}
