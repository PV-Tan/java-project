package com.phamvantan.exercise201.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phamvantan.exercise201.entity.Attribute;

import java.util.UUID;

public interface AttributeRepository extends JpaRepository<Attribute, UUID> {
}
