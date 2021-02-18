package com.dte2803.restservice.repositories;

import com.dte2803.restservice.entities.Category;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.LongToIntFunction;

/**
 * Repository to store Category entities
 * Stored in memory with an arraylist with initialized values
 */
@Repository
public class CategoryRepository implements BaseRepository<Category, Long> {
    private ArrayList<Category> categories = new ArrayList<Category>(
            Arrays.asList(new Category(1,"Network"), new Category(2,"Computer"), new Category(3,"PatientEquipment"), new Category(4, "Other"))
    );
    @Override
    public Category findById(long id) {
        return categories.get((int)id-1);
    }

    @Override
    public void save(Category category) {
    }

    @Override
    public ArrayList<Category> getAll() {
        return categories;
    }
}
