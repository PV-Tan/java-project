package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.UUID;

import com.phamvantan.exercise201.entity.VariantOption;

public interface VariantOptionService {
    
    // Tạo mới một VariantOption
    VariantOption createVariantOption(VariantOption variantOption);

    // Lấy VariantOption theo ID
    VariantOption getVariantOptionById(UUID id);

    // Lấy tất cả VariantOptions
    List<VariantOption> getAllVariantOptions();

    // Cập nhật VariantOption
    VariantOption updateVariantOption(UUID id, VariantOption variantOptionDetails);

    // Xóa VariantOption
    void deleteVariantOption(UUID id);
}