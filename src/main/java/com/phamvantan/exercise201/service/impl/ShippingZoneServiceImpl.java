package com.phamvantan.exercise201.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phamvantan.exercise201.entity.ShippingZone;
import com.phamvantan.exercise201.entity.StaffAccount;
import com.phamvantan.exercise201.repository.ShippingZoneRepository;
import com.phamvantan.exercise201.repository.StaffAccountRepository;
import com.phamvantan.exercise201.service.ShippingZoneService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShippingZoneServiceImpl implements ShippingZoneService {

    private final ShippingZoneRepository shippingZoneRepository;
    private final StaffAccountRepository staffAccountRepository;

    // Helper method to find StaffAccount or throw exception
    private StaffAccount getStaffAccount(UUID staffId) {
        return staffAccountRepository.findById(staffId)
                .orElseThrow(() -> new EntityNotFoundException("StaffAccount not found with id: " + staffId));
    }

    @Override
    public List<ShippingZone> findAll() {
        return shippingZoneRepository.findAll();
    }

    @Override
    public Optional<ShippingZone> findById(UUID id) {
        return shippingZoneRepository.findById(id);
    }

    @Override
    @Transactional
    public ShippingZone save(ShippingZone shippingZone, UUID staffId) {
        // Fetch full StaffAccount entity to set 'createdBy' and 'updatedBy'
        StaffAccount staff = getStaffAccount(staffId);

        shippingZone.setCreatedBy(staff);
        shippingZone.setUpdatedBy(staff);

        return shippingZoneRepository.save(shippingZone);
    }

    @Override
    @Transactional
    public ShippingZone update(UUID id, ShippingZone shippingZoneDetails, UUID staffId) {
        ShippingZone existingZone = shippingZoneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ShippingZone not found with id: " + id));

        // Fetch full StaffAccount entity for 'updatedBy'
        StaffAccount staff = getStaffAccount(staffId);

        // Update fields
        existingZone.setName(shippingZoneDetails.getName());
        existingZone.setDisplayName(shippingZoneDetails.getDisplayName());
        existingZone.setActive(shippingZoneDetails.getActive());
        existingZone.setFreeShipping(shippingZoneDetails.getFreeShipping());
        existingZone.setRateType(shippingZoneDetails.getRateType());
        
        // Set updatedBy
        existingZone.setUpdatedBy(staff);

        return shippingZoneRepository.save(existingZone);
    }

    @Override
    public void deleteById(UUID id) {
        shippingZoneRepository.deleteById(id);
    }
}