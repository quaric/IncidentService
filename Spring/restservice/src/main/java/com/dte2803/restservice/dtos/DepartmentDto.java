package com.dte2803.restservice.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

/**
 * Department entities are mapped using MapStruct into a DepartmentDTO object before being returned to user
 */
public class DepartmentDto extends RepresentationModel<DepartmentDto> {
    private Long id;
    private String name;

    @JsonCreator
    public DepartmentDto(@JsonProperty("id") Long id,@JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
