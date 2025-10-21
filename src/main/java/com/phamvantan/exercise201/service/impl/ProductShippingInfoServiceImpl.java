package com.phamvantan.exercise201.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.phamvantan.exercise201.entity.ProductShippingInfo;
import com.phamvantan.exercise201.repository.ProductShippingInfoRepository;
import com.phamvantan.exercise201.service.ProductShippingInfoService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductShippingInfoServiceImpl implements ProductShippingInfoService {

    private final ProductShippingInfoRepository repository;

    @Override
    public List<ProductShippingInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public ProductShippingInfo findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ProductShippingInfo findByProductId(UUID productId) {
        return repository.findByProduct_Id(productId);
    }

    @Override
    public ProductShippingInfo save(ProductShippingInfo shippingInfo) {
        return repository.save(shippingInfo);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
