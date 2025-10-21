package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.phamvantan.exercise201.entity.AttributeValue;

public interface AttributeValueService {

    // Lấy tất cả các AttributeValue, có kèm thông tin đầy đủ của Attribute và StaffAccount
    List<AttributeValue> findAllWithDetails();

    Optional<AttributeValue> findById(UUID id);

    AttributeValue save(AttributeValue attributeValue);

    void deleteById(UUID id);
}