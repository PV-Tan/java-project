package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.phamvantan.exercise201.entity.Gallery;

public interface GalleryService {
    List<Gallery> findAll();
    Optional<Gallery> findById(UUID id);
    Gallery save(Gallery gallery);
    void deleteById(UUID id);
    
    List<Gallery> findGalleriesByProductId(UUID productId);
}