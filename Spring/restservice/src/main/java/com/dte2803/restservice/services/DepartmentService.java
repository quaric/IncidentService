package com.dte2803.restservice.services;

import com.dte2803.restservice.controllers.DepartmentController;
import com.dte2803.restservice.dtos.DepartmentDto;
import com.dte2803.restservice.entities.Department;
import com.dte2803.restservice.mappers.DepartmentMapper;
import com.dte2803.restservice.repositories.DepartmentRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * service class for business logic
 * acts as a layer between the repository and the controller
 */
@Service
public class DepartmentService {
    private DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public ResponseEntity<DepartmentDto> getDepartment(long id) {
        Department department = departmentRepository.findById(id);
        DepartmentDto dto = DepartmentMapper.INSTANCE.departmentToDepartmentDto(department);
        dto.add(linkTo(methodOn(DepartmentController.class).get(dto.getId())).withSelfRel());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public CollectionModel<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.getAll();
        List<DepartmentDto> departmentDtos = new ArrayList<>();

        for(Department d : departments) {
            DepartmentDto dto = DepartmentMapper.INSTANCE.departmentToDepartmentDto(d);
            dto.add(linkTo(methodOn(DepartmentController.class).get(dto.getId())).withSelfRel());
            departmentDtos.add(dto);
        }
        return new CollectionModel<>(departmentDtos);

    }
}
