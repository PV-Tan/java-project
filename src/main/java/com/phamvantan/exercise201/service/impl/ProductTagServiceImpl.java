package com.phamvantan.exercise201.service.impl;

import com.phamvantan.exercise201.entity.ProductTag;
import com.phamvantan.exercise201.repository.ProductTagRepository;
import com.phamvantan.exercise201.service.ProductTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductTagServiceImpl implements ProductTagService {

    private final ProductTagRepository productTagRepository;

    @Override
    public List<ProductTag> findAll() {
        return productTagRepository.findAll();
    }

    @Override
    public ProductTag findById(UUID id) {
        return productTagRepository.findById(id).orElse(null);
    }

    @Override
    public ProductTag save(ProductTag productTag) {
        return productTagRepository.save(productTag);
    }

    @Override
    public void deleteById(UUID id) {
        productTagRepository.deleteById(id);
    }

    @Override
    public List<ProductTag> findByProductId(UUID productId) {
        return productTagRepository.findByProduct_Id(productId);
    }

    @Override
    public List<ProductTag> findByTagId(UUID tagId) {
        return productTagRepository.findByTag_Id(tagId);
    }
}
