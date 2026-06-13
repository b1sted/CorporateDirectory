package ru.basted.corporatedirectory.service.impl;

import jakarta.transaction.Transactional;
import ru.basted.corporatedirectory.dto.EmployeeCreateDto;
import ru.basted.corporatedirectory.dto.EmployeeResponseDto;
import ru.basted.corporatedirectory.exception.EmailAlreadyExistsException;
import ru.basted.corporatedirectory.exception.ResourceNotFoundException;
import ru.basted.corporatedirectory.mapper.EmployeeMapper;
import ru.basted.corporatedirectory.model.Employee;
import ru.basted.corporatedirectory.repository.EmployeeRepository;
import ru.basted.corporatedirectory.service.EmployeeService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new ResourceNotFoundException("Сотрудник c id " + id + " не найден"));

        return mapper.toResponseDto(employee);
    }

    @Override
    public EmployeeResponseDto createEmployee(EmployeeCreateDto createDto) {
        if (repository.existsByEmail(createDto.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "Пользователь с email " + createDto.getEmail() + " уже существует"
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
                .orElseThrow(() -> new ResourceNotFoundException("Сотрудник c id " + id + " не найден"));

        String newEmail = createDto.getEmail();
        if (!existingEmployee.getEmail().equals(newEmail) && repository.existsByEmail(newEmail)) {
            throw new EmailAlreadyExistsException(
                    "Пользователь с email " + createDto.getEmail() + " уже существует"
            );
        }

        mapper.updateEntityFromDto(createDto, existingEmployee);

        Employee savedEmployee = repository.save(existingEmployee);
        return mapper.toResponseDto(savedEmployee);
    }

    @Override
    public void removeEmployee(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Сотрудник c id " + id + " не найден");
        }

        repository.deleteById(id);
    }
}
