package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.UUID;

import com.phamvantan.exercise201.entity.VariantValue;

public interface VariantValueService {
    
    // Tạo mới một VariantValue
    VariantValue createVariantValue(VariantValue variantValue);

    // Lấy VariantValue theo ID
    VariantValue getVariantValueById(UUID id);

    // Lấy tất cả VariantValues
    List<VariantValue> getAllVariantValues();

    // Cập nhật VariantValue
    VariantValue updateVariantValue(UUID id, VariantValue variantValueDetails);

    // Xóa VariantValue
    void deleteVariantValue(UUID id);
}