package com.phamvantan.exercise201.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phamvantan.exercise201.entity.AttributeValue;
import com.phamvantan.exercise201.repository.AttributeValueRepository;
import com.phamvantan.exercise201.service.AttributeValueService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttributeValueServiceImpl implements AttributeValueService {

    private final AttributeValueRepository attributeValueRepository;

    public AttributeValueServiceImpl(AttributeValueRepository attributeValueRepository) {
        this.attributeValueRepository = attributeValueRepository;
    }

    // Phương thức thực hiện yêu cầu liệt kê với đầy đủ thông tin (đã được fetch qua EntityGraph)
    @Override
    @Transactional(readOnly = true)
    public List<AttributeValue> findAllWithDetails() {
        return attributeValueRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AttributeValue> findById(UUID id) {
        // Sử dụng phương thức custom để đảm bảo Attribute và StaffAccount được fetch
        return Optional.ofNullable(attributeValueRepository.findAttributeValueById(id));
    }

    @Override
    @Transactional
    public AttributeValue save(AttributeValue attributeValue) {
        // Lưu ý: Đảm bảo trường 'attribute' trong request body là một Entity đã tồn tại
        return attributeValueRepository.save(attributeValue);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        attributeValueRepository.deleteById(id);
    }
}