package com.dte2803.restservice.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

/**
 * Category entity which is for server only use, is not transformed to DTO or shown to user in any requests
 */
public class Category {
    private long id;
    private String name;
    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public long getId() {
        return id;
    }

}
