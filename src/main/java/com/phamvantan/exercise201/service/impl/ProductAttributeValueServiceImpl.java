package com.phamvantan.exercise201.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.phamvantan.exercise201.entity.ProductAttributeValue;
import com.phamvantan.exercise201.repository.ProductAttributeValueRepository;
import com.phamvantan.exercise201.service.ProductAttributeValueService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductAttributeValueServiceImpl implements ProductAttributeValueService {

    private final ProductAttributeValueRepository productAttributeValueRepository;

    @Override
    public List<ProductAttributeValue> getAll() {
        return productAttributeValueRepository.findAll();
    }

    @Override
    public ProductAttributeValue getById(UUID id) {
        return productAttributeValueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductAttributeValue not found with id: " + id));
    }

    @Override
    public ProductAttributeValue create(ProductAttributeValue pav) {
        return productAttributeValueRepository.save(pav);
    }

    @Override
    public ProductAttributeValue update(UUID id, ProductAttributeValue updated) {
        ProductAttributeValue existing = getById(id);
        existing.setProductAttribute(updated.getProductAttribute());
        existing.setAttributeValue(updated.getAttributeValue());
        return productAttributeValueRepository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        productAttributeValueRepository.deleteById(id);
    }
}
