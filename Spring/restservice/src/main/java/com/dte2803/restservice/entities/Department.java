package com.dte2803.restservice.entities;

/**
 * Entity representing hospital department for server logic use
 * Is mapped to a DepartmentDTO object if requested by user
 */
public class Department {

    private Long id;
    private String name;

    public Department(Long id, String name) {
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
