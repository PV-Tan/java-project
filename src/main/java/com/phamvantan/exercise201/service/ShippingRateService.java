package com.phamvantan.exercise201.service; // Adjust package as needed

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.phamvantan.exercise201.entity.ShippingRate;

public interface ShippingRateService {

    ShippingRate save(ShippingRate shippingRate);

    Optional<ShippingRate> findById(UUID id);

    List<ShippingRate> findAll();

    void deleteById(UUID id);
    
    // Example: Add more business methods here
    // List<ShippingRate> getRatesByZone(UUID shippingZoneId);
}