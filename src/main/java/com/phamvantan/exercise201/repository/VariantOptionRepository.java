package com.phamvantan.exercise201.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamvantan.exercise201.entity.VariantOption;

import java.util.UUID;

@Repository
public interface VariantOptionRepository extends JpaRepository<VariantOption, UUID> {
    // Spring Data JPA tự động cung cấp các phương thức CRUD
    // và có thể dễ dàng định nghĩa các phương thức query tùy chỉnh
    // Ví dụ: List<VariantOption> findByProductId(UUID productId);
}