package ru.basted.corporatedirectory.mapper;

import ru.basted.corporatedirectory.dto.employee.EmployeeCreateDto;
import ru.basted.corporatedirectory.dto.employee.EmployeeResponseDto;
import ru.basted.corporatedirectory.model.Employee;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(EmployeeCreateDto dto);
    void updateEntityFromDto(EmployeeCreateDto dto, @MappingTarget Employee entity);
    EmployeeResponseDto toResponseDto(Employee entity);
}
