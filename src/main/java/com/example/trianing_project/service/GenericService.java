package com.example.trianing_project.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GenericService<T, E> {
    List<T> findAll();
    Page<T> findAll(String search, Pageable pageable);
    Optional<T> findOne(E id);
    T save(T t);
    void delete(E id);

}
