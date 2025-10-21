package com.phamvantan.exercise201.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phamvantan.exercise201.entity.ProductAttributeValue;

import java.util.UUID;

public interface ProductAttributeValueRepository extends JpaRepository<ProductAttributeValue, UUID> {
}
