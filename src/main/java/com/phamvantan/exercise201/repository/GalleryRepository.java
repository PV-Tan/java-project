package com.phamvantan.exercise201.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamvantan.exercise201.entity.Gallery;

import java.util.List;
import java.util.UUID;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, UUID> {
    // Phương thức tìm tất cả các ảnh theo product_id
    List<Gallery> findByProductId(UUID productId);
    
    // Phương thức tìm ảnh thumbnail (is_thumbnail = true) theo product_id
    List<Gallery> findByProductIdAndIsThumbnail(UUID productId, Boolean isThumbnail);
}