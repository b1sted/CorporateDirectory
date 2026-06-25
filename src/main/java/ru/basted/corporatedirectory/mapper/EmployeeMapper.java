package ru.basted.corporatedirectory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import ru.basted.corporatedirectory.dto.employee.EmployeeCreateDto;
import ru.basted.corporatedirectory.dto.employee.EmployeeResponseDto;
import ru.basted.corporatedirectory.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(EmployeeCreateDto dto);
    void updateEntityFromDto(EmployeeCreateDto dto, @MappingTarget Employee entity);
    EmployeeResponseDto toResponseDto(Employee entity);
}
