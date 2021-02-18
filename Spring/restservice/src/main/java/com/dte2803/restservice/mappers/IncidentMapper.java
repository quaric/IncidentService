package com.dte2803.restservice.mappers;

import com.dte2803.restservice.dtos.IncidentDto;
import com.dte2803.restservice.entities.Incident;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
/**
 * Mapper created with MapStruct
 * Maps Incident entities into IncidentDTOs and other way
 */
@Mapper(uses=DepartmentMapper.class)
public interface IncidentMapper {
    IncidentMapper INSTANCE = Mappers.getMapper(IncidentMapper.class);

    @Mappings({
            @Mapping(source="departmentId", target="department.id"),
            @Mapping(source="categoryId", target="category.id")
    })
    Incident incidentDtoToIncident(IncidentDto incidentDto);

    @Mappings({
            @Mapping(source="department.id",target="departmentId"),
            @Mapping(source="category.id",target="categoryId")
    })

    IncidentDto incidentToIncidentDto(Incident incident);

}
