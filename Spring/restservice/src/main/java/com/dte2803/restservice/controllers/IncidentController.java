package com.dte2803.restservice.controllers;

import com.dte2803.restservice.dtos.*;

import com.dte2803.restservice.services.IncidentService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;

/**
 * Standard REST controller for Incident-entities that uses a service class for business logic
 * Controller returns DTOs instead of entities
 */
@RestController
@RequestMapping("/incidents")
public class IncidentController {
    private final IncidentService incidentService;

    IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    /**
     *
     * @param id
     * @return incidentDTO with id, or NOT_FOUND if non-existing
     */
    @GetMapping("/{id}")
    public ResponseEntity<IncidentDto> get(@PathVariable final long id) {
        return incidentService.getIncident(id);
    }

    /**
     *
     * @param principal
     * @param incidentFromRequest
     * @return returns created entity in form of a DTO
     */
    @PostMapping
    public ResponseEntity<IncidentDto> post(Principal principal, @RequestBody IncidentDto incidentFromRequest) {
        return incidentService.postIncident(principal, incidentFromRequest);
    }

    /**
     *
     * @param principal
     * @return returns all incident entities registered with signed in users name as a CollectionModel<IncidentDTO> object
     */
    @GetMapping
    public CollectionModel<IncidentDto> getAll(Principal principal) {
        return incidentService.getAllIncidents(principal);
    }

    /**
     * Edits the given id if exists and is users
     * returns edited object as DTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<IncidentDto> put(Principal principal, @PathVariable long id, @RequestBody IncidentDto incidentFromRequest) {
        return incidentService.putIncident(principal, id, incidentFromRequest);
    }

    /**
     * deletes det given id if exists and is users
     * @param principal
     * @param id
     * @return OK 200
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<IncidentDto> delete(Principal principal, @PathVariable long id) {
        return incidentService.deleteIncident(principal, id);
    }

    /**
     * Returns allowed actions for "incidents/{id}"
     * @return
     */
    @RequestMapping(value="/{id}", method= RequestMethod.OPTIONS)
    public ResponseEntity<?> singularOptions() {
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET,HttpMethod.DELETE,HttpMethod.PUT,HttpMethod.OPTIONS)
                .build();
    }

    /**
     * Returns allowed actions for "incidents"
     * @return
     */
    @RequestMapping(method=RequestMethod.OPTIONS)
    public ResponseEntity<?> collectionOptions() {
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET,HttpMethod.POST,HttpMethod.OPTIONS)
                .build();
    }
}
