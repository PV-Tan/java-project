package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.phamvantan.exercise201.entity.ShippingZone;

public interface ShippingZoneService {
    List<ShippingZone> findAll();
    Optional<ShippingZone> findById(UUID id);
    ShippingZone save(ShippingZone shippingZone, UUID staffId);
    void deleteById(UUID id);
    ShippingZone update(UUID id, ShippingZone shippingZoneDetails, UUID staffId);
}