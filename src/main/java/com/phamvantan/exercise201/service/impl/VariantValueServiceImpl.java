package com.phamvantan.exercise201.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.phamvantan.exercise201.entity.VariantValue;
import com.phamvantan.exercise201.repository.VariantValueRepository;
import com.phamvantan.exercise201.service.VariantValueService;

import java.util.List;
import java.util.UUID;

@Service
public class VariantValueServiceImpl implements VariantValueService {

    private final VariantValueRepository variantValueRepository;

    // Dependency Injection qua constructor
    public VariantValueServiceImpl(VariantValueRepository variantValueRepository) {
        this.variantValueRepository = variantValueRepository;
    }

    @Override
    public VariantValue createVariantValue(VariantValue variantValue) {
        return variantValueRepository.save(variantValue);
    }

    @Override
    public VariantValue getVariantValueById(UUID id) {
        // Trả về VariantValue kèm thông tin chi tiết của Variant và ProductAttributeValue
        return variantValueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VariantValue not found with id: " + id));
    }

    @Override
    public List<VariantValue> getAllVariantValues() {
        return variantValueRepository.findAll();
    }

    @Override
    public VariantValue updateVariantValue(UUID id, VariantValue variantValueDetails) {
        VariantValue existingValue = variantValueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VariantValue not found with id: " + id));

        // Cập nhật mối quan hệ
        existingValue.setVariant(variantValueDetails.getVariant()); 
        existingValue.setProductAttributeValue(variantValueDetails.getProductAttributeValue());

        return variantValueRepository.save(existingValue);
    }

    @Override
    public void deleteVariantValue(UUID id) {
        if (!variantValueRepository.existsById(id)) {
            throw new EntityNotFoundException("VariantValue not found with id: " + id);
        }
        variantValueRepository.deleteById(id);
    }
}