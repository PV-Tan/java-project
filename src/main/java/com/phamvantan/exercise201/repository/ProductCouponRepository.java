package com.phamvantan.exercise201.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phamvantan.exercise201.entity.ProductCoupon;

import java.util.UUID;

public interface ProductCouponRepository extends JpaRepository<ProductCoupon, UUID> {
    // Có thể thêm các phương thức tìm kiếm tùy chỉnh tại đây nếu cần
    // Ví dụ: List<ProductCoupon> findByProductId(UUID productId);
}