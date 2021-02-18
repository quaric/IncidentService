package com.dte2803.restservice.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;
/**
 * Entity representing an incident for server logic use
 * Is mapped to a IncidentDTO object if requested by user
 */
public class Incident {
    private Long id;
    private String username;
    private String priority;
    private Department department;
    private String room;
    private String equipmentId;
    private String smallDescription;
    private String description;
    private Category category;
    /**
     * All fields for PatientEquipment except id + username which is set by repo and controller
     */
    public Incident(String priority, Department department, String room, String equipmentId,
                    String smallDescription,String description,
                    Category category) {
        this.priority = priority;
        this.department = department;
        this.room = room;
        this.equipmentId = equipmentId;
        this.smallDescription = smallDescription;
        this.description = description;
        this.category = category;
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

    public Department getDepartment() {return department;}
    public Category getCategory() {return category;}

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
