package com.phamvantan.exercise201.service;

import com.phamvantan.exercise201.entity.ProductTag;
import java.util.List;
import java.util.UUID;

public interface ProductTagService {
    List<ProductTag> findAll();
    ProductTag findById(UUID id);
    ProductTag save(ProductTag productTag);
    void deleteById(UUID id);
    List<ProductTag> findByProductId(UUID productId);
    List<ProductTag> findByTagId(UUID tagId);
}
