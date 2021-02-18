package com.dte2803.restservice.repositories;

import com.dte2803.restservice.entities.Department;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Repository to store Department entities
 * Stored in memory with an arraylist with initialized values
 */
@Repository
public class DepartmentRepository implements BaseRepository<Department, Long> {
    private ArrayList<Department> departments = new ArrayList<Department>(
            Arrays.asList(new Department(1l, "Kirurgi"), new Department(2l, "Anestesi"), new Department(3l,"Akutt psykisk helse"), new Department(4l,"Intensiv"), new Department(5l,"Nevrologisk"))
    );

    @Override
    public Department findById(long id) {
        return departments.get((int)id-1);
    }

    @Override
    public void save(Department department) {
    }

    @Override
    public List<Department> getAll() {
        return departments;
    }
}
