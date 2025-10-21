package com.phamvantan.exercise201.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.phamvantan.exercise201.entity.Attribute;
import com.phamvantan.exercise201.repository.AttributeRepository;
import com.phamvantan.exercise201.service.AttributeService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttributeServiceImpl implements AttributeService {

    private final AttributeRepository attributeRepository;

    @Override
    public List<Attribute> getAll() {
        return attributeRepository.findAll();
    }

    @Override
    public Attribute getById(UUID id) {
        return attributeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attribute not found with id: " + id));
    }

    @Override
    public Attribute create(Attribute attribute) {
        return attributeRepository.save(attribute);
    }

    @Override
    public Attribute update(UUID id, Attribute attribute) {
        Attribute existing = getById(id);
        existing.setCreatedBy(attribute.getCreatedBy());
        existing.setUpdatedBy(attribute.getUpdatedBy());
        existing.setUpdatedAt(attribute.getUpdatedAt());
        return attributeRepository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        Attribute existing = getById(id);
        attributeRepository.delete(existing);
    }
}
