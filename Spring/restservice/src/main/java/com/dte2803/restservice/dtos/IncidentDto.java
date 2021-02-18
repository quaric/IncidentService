package com.dte2803.restservice.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;
/**
 * Incident entities are mapped using MapStruct into a IncidentDTO object before being returned to user
 */
public class IncidentDto extends RepresentationModel<IncidentDto> {
    private Long id;
    private String username; //TODO REMOVE?
    private String priority;
    private int departmentId;
    private String room;
    private String equipmentId;
    private String smallDescription;
    private String description;
    private int categoryId;

    /**
     * All fields for PatientEquipment except id + username which is set by repo and controller
     */
    @JsonCreator
    public IncidentDto(@JsonProperty("priority") String priority, @JsonProperty("departmentId") int departmentId,
                    @JsonProperty("room") String room, @JsonProperty("equipmentId") String equipmentId,
                    @JsonProperty("smallDescription") String smallDescription, @JsonProperty("description") String description,
                    @JsonProperty("category") int categoryId) {
        this.priority = priority;
        this.departmentId = departmentId;
        this.room = room;
        this.equipmentId = equipmentId;
        this.smallDescription = smallDescription;
        this.description = description;
        this.categoryId = categoryId;
    }



    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public int getDepartmentId() {
        return departmentId;
    }
    public int getCategoryId() {
        return categoryId;
    }

    public String getPriority() {
        return priority;
    }

    public String getRoom() {
        return room;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public String getSmallDescription() {
        return smallDescription;
    }

    public String getDescription() {
        return description;
    }
}
