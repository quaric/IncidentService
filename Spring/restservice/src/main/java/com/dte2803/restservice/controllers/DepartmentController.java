package com.dte2803.restservice.controllers;

import com.dte2803.restservice.dtos.DepartmentDto;

import com.dte2803.restservice.services.DepartmentService;
import org.springframework.hateoas.CollectionModel;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Standard REST controller for department-entities that uses a service class for business logic
 * Controller returns DTOs instead of entities
 */
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * returns departments with id from repository as departmentDTO
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> get(@PathVariable final long id) {
        return departmentService.getDepartment(id);
    }

    /**
     * returns all departments in repository as CollectionModel<departmentDTO>
     * @return
     */
    @GetMapping
    public CollectionModel<DepartmentDto> getAll() {
       return departmentService.getAllDepartments();
    }

    /**
     * Returns allowed actions for "/departments/{id}"
     * @return
     */
    @RequestMapping(value="/{id}", method= RequestMethod.OPTIONS)
    public ResponseEntity<?> singularOptions() {
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET,HttpMethod.OPTIONS)
                .build();
    }

    /**
     * Returns allowed actions for "/departments"
     * @return
     */
    @RequestMapping(method=RequestMethod.OPTIONS)
    public ResponseEntity<?> collectionOptions() {
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET,HttpMethod.OPTIONS)
                .build();
    }
}
