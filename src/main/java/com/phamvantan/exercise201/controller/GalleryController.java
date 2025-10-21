package com.phamvantan.exercise201.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phamvantan.exercise201.entity.Gallery;
import com.phamvantan.exercise201.service.GalleryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/galleries")
public class GalleryController {

    private final GalleryService galleryService;

    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    // GET /api/v1/galleries
    @GetMapping
    public ResponseEntity<List<Gallery>> getAllGalleries() {
        List<Gallery> galleries = galleryService.findAll();
        return ResponseEntity.ok(galleries);
    }

    // GET /api/v1/galleries/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Gallery> getGalleryById(@PathVariable UUID id) {
        return galleryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/v1/galleries/product/{productId} - Lấy tất cả ảnh của một Product
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Gallery>> getGalleriesByProductId(@PathVariable UUID productId) {
        List<Gallery> galleries = galleryService.findGalleriesByProductId(productId);
        if (galleries.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(galleries);
    }
    
    // POST /api/v1/galleries
    @PostMapping
    public ResponseEntity<Gallery> createGallery(@RequestBody Gallery gallery) {
        // Cần đảm bảo rằng product trong gallery đã được set (ví dụ: product.setId(productId))
        Gallery savedGallery = galleryService.save(gallery);
        return new ResponseEntity<>(savedGallery, HttpStatus.CREATED);
    }

    // PUT /api/v1/galleries/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Gallery> updateGallery(@PathVariable UUID id, @RequestBody Gallery galleryDetails) {
        return galleryService.findById(id)
                .map(existingGallery -> {
                    // Cập nhật các trường
                    existingGallery.setImage(galleryDetails.getImage());
                    existingGallery.setPlaceholder(galleryDetails.getPlaceholder());
                    existingGallery.setIsThumbnail(galleryDetails.getIsThumbnail());
                    // Đảm bảo không thay đổi product_id nếu không cần thiết
                    if (galleryDetails.getProduct() != null) {
                       existingGallery.setProduct(galleryDetails.getProduct());
                    }

                    Gallery updatedGallery = galleryService.save(existingGallery);
                    return ResponseEntity.ok(updatedGallery);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/v1/galleries/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGallery(@PathVariable UUID id) {
        if (!galleryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        galleryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}