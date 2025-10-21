package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.UUID;

import com.phamvantan.exercise201.entity.Variant;

public interface VariantService {
    
    // Tạo mới một Variant
    Variant createVariant(Variant variant);

    // Lấy Variant theo ID
    Variant getVariantById(UUID id);

    // Lấy tất cả Variants
    List<Variant> getAllVariants();

    // Cập nhật Variant
    Variant updateVariant(UUID id, Variant variantDetails);

    // Xóa Variant
    void deleteVariant(UUID id);
}