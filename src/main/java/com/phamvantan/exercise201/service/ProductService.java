package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.phamvantan.exercise201.entity.Product;

import java.util.Map;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(UUID id);
    Product save(Product product);
    Product update(UUID id, Map<String, Object> payload);
    void deleteById(UUID id);
}