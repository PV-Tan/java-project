package com.phamvantan.exercise201.service.impl;

import org.springframework.stereotype.Service;

import com.phamvantan.exercise201.entity.Gallery;
import com.phamvantan.exercise201.repository.GalleryRepository;
import com.phamvantan.exercise201.service.GalleryService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryRepository;

    public GalleryServiceImpl(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    @Override
    public List<Gallery> findAll() {
        return galleryRepository.findAll();
    }

    @Override
    public Optional<Gallery> findById(UUID id) {
        return galleryRepository.findById(id);
    }

    @Override
    public Gallery save(Gallery gallery) {
        if (gallery.getId() == null) {
            // Thiết lập createdAt và updatedAt khi tạo mới
            gallery.setCreatedAt(LocalDateTime.now());
        }
        // Cập nhật updatedAt
        gallery.setUpdatedAt(LocalDateTime.now());
        return galleryRepository.save(gallery);
    }

    @Override
    public void deleteById(UUID id) {
        galleryRepository.deleteById(id);
    }

    @Override
    public List<Gallery> findGalleriesByProductId(UUID productId) {
        return galleryRepository.findByProductId(productId);
    }
}