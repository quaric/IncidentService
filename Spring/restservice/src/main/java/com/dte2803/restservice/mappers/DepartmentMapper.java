package com.dte2803.restservice.mappers;

import com.dte2803.restservice.dtos.DepartmentDto;
import com.dte2803.restservice.entities.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper created with MapStruct
 * Maps Department entities into DepartmentDTOs and other way
 */
@Mapper
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
    Department departmentDtoToDepartment(DepartmentDto departmentDto);
    DepartmentDto departmentToDepartmentDto(Department department);

}
