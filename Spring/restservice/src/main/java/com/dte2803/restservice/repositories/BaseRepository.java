package com.dte2803.restservice.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseRepository<T,ID>  {
    T findById(long id);
    void save(T t);
    List<T> getAll();
}
