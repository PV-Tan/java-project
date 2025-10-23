package com.phamvantan.exercise201.repository;

import com.phamvantan.exercise201.entity.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductTagRepository extends JpaRepository<ProductTag, UUID> {
    List<ProductTag> findByProduct_Id(UUID productId);
    List<ProductTag> findByTag_Id(UUID tagId);
    boolean existsByProduct_IdAndTag_Id(UUID productId, UUID tagId);
}
