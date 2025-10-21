package com.phamvantan.exercise201.service.impl; // Adjust package as needed

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phamvantan.exercise201.entity.ShippingRate;
import com.phamvantan.exercise201.repository.ShippingRateRepository;
import com.phamvantan.exercise201.service.ShippingRateService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShippingRateServiceImpl implements ShippingRateService {

    private final ShippingRateRepository shippingRateRepository;

    @Override
    @Transactional
    public ShippingRate save(ShippingRate shippingRate) {
        // Business logic or validation can be added here before saving
        return shippingRateRepository.save(shippingRate);
    }

    @Override
    public Optional<ShippingRate> findById(UUID id) {
        return shippingRateRepository.findById(id);
    }

    @Override
    public List<ShippingRate> findAll() {
        return shippingRateRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        shippingRateRepository.deleteById(id);
    }
}