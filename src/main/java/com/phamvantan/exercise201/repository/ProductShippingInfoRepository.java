package com.phamvantan.exercise201.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamvantan.exercise201.entity.ProductShippingInfo;

import java.util.UUID;

@Repository
public interface ProductShippingInfoRepository extends JpaRepository<ProductShippingInfo, UUID> {
    // Có thể bổ sung query nếu cần, ví dụ tìm theo productId
    ProductShippingInfo findByProduct_Id(UUID productId);
}
