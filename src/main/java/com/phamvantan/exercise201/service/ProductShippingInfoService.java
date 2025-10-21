package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.UUID;

import com.phamvantan.exercise201.entity.ProductShippingInfo;

public interface ProductShippingInfoService {
    List<ProductShippingInfo> findAll();
    ProductShippingInfo findById(UUID id);
    ProductShippingInfo findByProductId(UUID productId);
    ProductShippingInfo save(ProductShippingInfo shippingInfo);
    void delete(UUID id);
}
