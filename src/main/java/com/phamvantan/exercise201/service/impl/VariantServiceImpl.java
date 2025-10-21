package com.phamvantan.exercise201.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.phamvantan.exercise201.entity.Variant;
import com.phamvantan.exercise201.repository.VariantRepository;
import com.phamvantan.exercise201.service.VariantService;

import java.util.List;
import java.util.UUID;

@Service
public class VariantServiceImpl implements VariantService {

    private final VariantRepository variantRepository;

    // Dependency Injection qua constructor
    public VariantServiceImpl(VariantRepository variantRepository) {
        this.variantRepository = variantRepository;
    }

    @Override
    public Variant createVariant(Variant variant) {
        return variantRepository.save(variant);
    }

    @Override
    public Variant getVariantById(UUID id) {
        // Trả về Variant kèm thông tin Product và VariantOption đầy đủ (trong transaction)
        return variantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Variant not found with id: " + id));
    }

    @Override
    public List<Variant> getAllVariants() {
        return variantRepository.findAll();
    }

    @Override
    public Variant updateVariant(UUID id, Variant variantDetails) {
        Variant existingVariant = variantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Variant not found with id: " + id));

        existingVariant.setVariantOption(variantDetails.getVariantOption());
        
        // Cập nhật các mối quan hệ (product, variantOptionRef)
        existingVariant.setProduct(variantDetails.getProduct()); 
        existingVariant.setVariantOptionRef(variantDetails.getVariantOptionRef());

        return variantRepository.save(existingVariant);
    }

    @Override
    public void deleteVariant(UUID id) {
        if (!variantRepository.existsById(id)) {
            throw new EntityNotFoundException("Variant not found with id: " + id);
        }
        variantRepository.deleteById(id);
    }
}