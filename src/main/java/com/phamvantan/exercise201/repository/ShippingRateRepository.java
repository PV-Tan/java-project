package com.phamvantan.exercise201.repository; // Adjust package as needed

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamvantan.exercise201.entity.ShippingRate;

import java.util.UUID;

@Repository
public interface ShippingRateRepository extends JpaRepository<ShippingRate, UUID> {
    // Custom repository methods can be added here if needed, 
    // e.g., find rates by shipping zone ID
    // List<ShippingRate> findAllByShippingZone_Id(UUID shippingZoneId);
}