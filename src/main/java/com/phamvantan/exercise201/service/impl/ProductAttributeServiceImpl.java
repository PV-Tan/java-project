package com.phamvantan.exercise201.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.phamvantan.exercise201.entity.ProductAttribute;
import com.phamvantan.exercise201.repository.ProductAttributeRepository;
import com.phamvantan.exercise201.service.ProductAttributeService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductAttributeServiceImpl implements ProductAttributeService {

    private final ProductAttributeRepository productAttributeRepository;

    @Override
    public List<ProductAttribute> findAll() {
        return productAttributeRepository.findAll();
    }

    @Override
    public ProductAttribute findById(UUID id) {
        return productAttributeRepository.findById(id).orElse(null);
    }

    @Override
    public ProductAttribute save(ProductAttribute productAttribute) {
        return productAttributeRepository.save(productAttribute);
    }

    @Override
    public void deleteById(UUID id) {
        productAttributeRepository.deleteById(id);
    }
}
