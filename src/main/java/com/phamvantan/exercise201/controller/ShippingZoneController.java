package com.phamvantan.exercise201.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phamvantan.exercise201.entity.ShippingZone;
import com.phamvantan.exercise201.service.ShippingZoneService;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/shipping-zones")
@RequiredArgsConstructor
public class ShippingZoneController {

    private final ShippingZoneService shippingZoneService;

    @GetMapping
    public List<ShippingZone> getAllShippingZones() {
        return shippingZoneService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShippingZone> getShippingZoneById(@PathVariable UUID id) {
        return shippingZoneService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Không dùng header, lấy staffId từ shippingZone.createdBy trong body
    @PostMapping
    public ResponseEntity<ShippingZone> createShippingZone(@RequestBody ShippingZone shippingZone) {
        UUID staffId = Optional.ofNullable(shippingZone.getCreatedBy())
                               .map(c -> c.getId())
                               .orElse(null);

        ShippingZone savedZone = shippingZoneService.save(shippingZone, staffId);
        return ResponseEntity.created(URI.create("/api/shipping-zones/" + savedZone.getId()))
                             .body(savedZone);
    }

    // ✅ Không dùng header, lấy staffId từ shippingZoneDetails.updatedBy trong body
    @PutMapping("/{id}")
    public ResponseEntity<ShippingZone> updateShippingZone(
            @PathVariable UUID id,
            @RequestBody ShippingZone shippingZoneDetails) {

        UUID staffId = Optional.ofNullable(shippingZoneDetails.getUpdatedBy())
                               .map(u -> u.getId())
                               .orElse(null);

        try {
            ShippingZone updatedZone = shippingZoneService.update(id, shippingZoneDetails, staffId);
            return ResponseEntity.ok(updatedZone);
        } catch (jakarta.persistence.EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShippingZone(@PathVariable UUID id) {
        shippingZoneService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
