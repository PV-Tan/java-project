package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.UUID;

import com.phamvantan.exercise201.entity.ProductAttributeValue;

public interface ProductAttributeValueService {
    List<ProductAttributeValue> getAll();
    ProductAttributeValue getById(UUID id);
    ProductAttributeValue create(ProductAttributeValue pav);
    ProductAttributeValue update(UUID id, ProductAttributeValue pav);
    void delete(UUID id);
}
