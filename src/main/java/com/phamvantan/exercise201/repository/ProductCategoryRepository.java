package com.phamvantan.exercise201.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamvantan.exercise201.entity.ProductCategory;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {

    // Lấy tất cả category theo productId
    List<ProductCategory> findByProduct_Id(UUID productId);

    // Lấy tất cả product theo categoryId
    List<ProductCategory> findByCategory_Id(UUID categoryId);

    // Kiểm tra product có thuộc category chưa
    boolean existsByProduct_IdAndCategory_Id(UUID productId, UUID categoryId);

    // Xóa liên kết product-category
    void deleteByProduct_IdAndCategory_Id(UUID productId, UUID categoryId);
}
