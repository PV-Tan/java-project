package com.phamvantan.exercise201.controller; // Adjust package as needed

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phamvantan.exercise201.entity.ShippingRate;
import com.phamvantan.exercise201.service.ShippingRateService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shipping-rates") // Base URI for this resource
@RequiredArgsConstructor
public class ShippingRateController {

    private final ShippingRateService shippingRateService;

    // GET all shipping rates
    @GetMapping
    public ResponseEntity<List<ShippingRate>> getAllShippingRates() {
        List<ShippingRate> rates = shippingRateService.findAll();
        return ResponseEntity.ok(rates);
    }

    // GET a shipping rate by ID
    @GetMapping("/{id}")
    public ResponseEntity<ShippingRate> getShippingRateById(@PathVariable UUID id) {
        return shippingRateService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST create a new shipping rate
    @PostMapping
    public ResponseEntity<ShippingRate> createShippingRate(@RequestBody ShippingRate shippingRate) {
        ShippingRate savedRate = shippingRateService.save(shippingRate);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRate);
    }

    // PUT update an existing shipping rate
    @PutMapping("/{id}")
    public ResponseEntity<ShippingRate> updateShippingRate(@PathVariable UUID id, @RequestBody ShippingRate shippingRateDetails) {
        return shippingRateService.findById(id)
                .map(existingRate -> {
                    // Update fields from shippingRateDetails
                    existingRate.setShippingZone(shippingRateDetails.getShippingZone());
                    existingRate.setWeightUnit(shippingRateDetails.getWeightUnit());
                    existingRate.setMinValue(shippingRateDetails.getMinValue());
                    existingRate.setMaxValue(shippingRateDetails.getMaxValue());
                    existingRate.setNoMax(shippingRateDetails.getNoMax());
                    existingRate.setPrice(shippingRateDetails.getPrice());
                    
                    ShippingRate updatedRate = shippingRateService.save(existingRate);
                    return ResponseEntity.ok(updatedRate);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE a shipping rate
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShippingRate(@PathVariable UUID id) {
        if (shippingRateService.findById(id).isPresent()) {
            shippingRateService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}