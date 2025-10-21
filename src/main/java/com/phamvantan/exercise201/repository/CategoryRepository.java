package com.phamvantan.exercise201.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phamvantan.exercise201.entity.Category;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
