package com.dte2803.restservice.services;

import com.dte2803.restservice.controllers.DepartmentController;
import com.dte2803.restservice.controllers.IncidentController;
import com.dte2803.restservice.dtos.IncidentDto;
import com.dte2803.restservice.entities.Incident;
import com.dte2803.restservice.mappers.IncidentMapper;
import com.dte2803.restservice.repositories.CategoryRepository;
import com.dte2803.restservice.repositories.DepartmentRepository;
import com.dte2803.restservice.repositories.IncidentRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
/**
 * service class for business logic
 * acts as a layer between the repository and the controller
 */
@Service
public class IncidentService {
    private final IncidentRepository incidentRepository;
    private final CategoryRepository categoryRepository;
    private final DepartmentRepository departmentRepository;

    IncidentService(IncidentRepository incidentRepository, CategoryRepository categoryRepository,
                       DepartmentRepository departmentRepository) {
        this.incidentRepository = incidentRepository;
        this.categoryRepository = categoryRepository;
        this.departmentRepository = departmentRepository;
    }

    /**
     *
     * @param id
     * @return checks repository and returns incident with hateoas links of given id
     */
    public ResponseEntity<IncidentDto> getIncident(long id) {
        Incident incident = incidentRepository.findById(id);
        if(incident == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        IncidentDto dto = IncidentMapper.INSTANCE.incidentToIncidentDto(incident); //Converts the incident to DTO using mapper
        dto.setUsername(incident.getUsername());
        dto.add(linkTo(methodOn(IncidentController.class).get(id)).withSelfRel()); //Creates hateoas link to self
        dto.add(linkTo(methodOn(DepartmentController.class).get(dto.getDepartmentId())).withRel("department")); //Creates hateoas link to department object
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * creates an incident and returns it with hateoas links
     * @param principal
     * @param incidentFromRequest
     * @return
     */
    public ResponseEntity<IncidentDto> postIncident(Principal principal, IncidentDto incidentFromRequest) {
        String name = principal.getName();
        Incident incident = IncidentMapper.INSTANCE.incidentDtoToIncident(incidentFromRequest); //Converts the received incidentDTO Object to an incident object
        incident.setUsername(name); //Sets username as logged in
        incidentRepository.save(incident);
        IncidentDto dto = IncidentMapper.INSTANCE.incidentToIncidentDto(incident); //Converts the incident back to DTO
        dto.setUsername(incident.getUsername());
        dto.add(linkTo(methodOn(IncidentController.class).get(dto.getId())).withSelfRel()); //Creates hateoas link to self
        dto.add(linkTo(methodOn(DepartmentController.class).get(dto.getDepartmentId())).withRel("department")); //Creates hateoas link to department object
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    /**
     * returns all incidents for logged in user
     * creates a collectionmodel which stores all the different incidents
     * creates hateoas links for each individual incident
     * @param principal
     * @return
     */
    public CollectionModel<IncidentDto> getAllIncidents(Principal principal) {
        String name = principal.getName();
        List<Incident> userIncidents = incidentRepository.getAllCasesForUser(principal.getName());
        if(userIncidents.isEmpty()) return new CollectionModel<>(CollectionModel.empty());
        List<IncidentDto> incidentDtos = new ArrayList<>();
        //This loop converts all the incidents to DTOs, adds hateoas links and adds them one by one to the new IncidentDTO list
        for(Incident i : userIncidents) {
            IncidentDto dto = IncidentMapper.INSTANCE.incidentToIncidentDto(i);
            dto.add(linkTo(methodOn(IncidentController.class).get(dto.getId())).withSelfRel());
            dto.add(linkTo(methodOn(DepartmentController.class).get(dto.getDepartmentId())).withRel("department"));
            dto.setUsername(name);
            incidentDtos.add(dto);
        }
        return new CollectionModel<>(incidentDtos);
    }

    /**
     * checks if an incident exists, changes it with the new values, saves and returns it
     * @param principal
     * @param id
     * @param incidentFromRequest
     * @return
     */
    public ResponseEntity<IncidentDto> putIncident(Principal principal, long id, IncidentDto incidentFromRequest) {
        if(!incidentRepository.exists(id)) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        String name = principal.getName();
        incidentFromRequest.setId(id);
        Incident incident = IncidentMapper.INSTANCE.incidentDtoToIncident(incidentFromRequest);
        incident.setUsername(name);
        incidentRepository.put(incident);
        IncidentDto dto = IncidentMapper.INSTANCE.incidentToIncidentDto(incident);
        dto.add(linkTo(methodOn(IncidentController.class).get(dto.getId())).withSelfRel());
        dto.add(linkTo(methodOn(DepartmentController.class).get(dto.getDepartmentId())).withRel("department")); //Creates hateoas link to department object
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * deletes the incident if loggedin user owns it and it exists
     * @param principal
     * @param id
     * @return
     */
    public ResponseEntity<IncidentDto> deleteIncident(Principal principal, long id) {
        if(!incidentRepository.exists(id)) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        String name = principal.getName();
        Incident incident = incidentRepository.findById(id);
        if(!incident.getUsername().equals(name)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        incidentRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
