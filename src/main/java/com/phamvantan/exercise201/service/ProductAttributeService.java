package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.UUID;

import com.phamvantan.exercise201.entity.ProductAttribute;

public interface ProductAttributeService {
    List<ProductAttribute> findAll();
    ProductAttribute findById(UUID id);
    ProductAttribute save(ProductAttribute productAttribute);
    void deleteById(UUID id);
}
