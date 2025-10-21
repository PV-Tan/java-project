package com.phamvantan.exercise201.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.phamvantan.exercise201.entity.Category;
import com.phamvantan.exercise201.repository.CategoryRepository;
import com.phamvantan.exercise201.service.CategoryService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Category findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
