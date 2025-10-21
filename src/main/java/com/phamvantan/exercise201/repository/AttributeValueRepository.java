package com.phamvantan.exercise201.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamvantan.exercise201.entity.AttributeValue;

import java.util.List;
import java.util.UUID;

@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValue, UUID> {

    // Fetch Attribute, Attribute.createdBy, và Attribute.updatedBy để lấy đầy đủ thông tin
    @Override
    @EntityGraph(attributePaths = {"attribute", "attribute.createdBy", "attribute.updatedBy"})
    List<AttributeValue> findAll();
    
    // Tìm kiếm theo ID và fetch các thông tin chi tiết
    @EntityGraph(attributePaths = {"attribute", "attribute.createdBy", "attribute.updatedBy"})
    AttributeValue findAttributeValueById(UUID id);
}