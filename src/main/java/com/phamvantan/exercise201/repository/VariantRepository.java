package com.phamvantan.exercise201.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamvantan.exercise201.entity.Variant;

import java.util.UUID;

@Repository
public interface VariantRepository extends JpaRepository<Variant, UUID> {
    // Thêm các phương thức truy vấn tùy chỉnh nếu cần, ví dụ:
    // List<Variant> findByProductId(UUID productId);
}