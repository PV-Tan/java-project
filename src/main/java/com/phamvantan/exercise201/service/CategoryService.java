package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.UUID;

import com.phamvantan.exercise201.entity.Category;

public interface CategoryService {
    List<Category> findAll();
    Category findById(UUID id);
    Category save(Category category);
    void deleteById(UUID id);
}
