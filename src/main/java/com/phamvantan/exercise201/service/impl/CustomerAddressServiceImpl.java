package com.phamvantan.exercise201.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.phamvantan.exercise201.entity.CustomerAddress;
import com.phamvantan.exercise201.repository.CustomerAddressRepository;
import com.phamvantan.exercise201.service.CustomerAddressService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerAddressServiceImpl implements CustomerAddressService {

    private final CustomerAddressRepository customerAddressRepository;

    // 1. TRIỂN KHAI GET ALL: Sử dụng repository.findAll() để lấy tất cả bản ghi
    @Override
    public List<CustomerAddress> getAllAddresses() {
        return customerAddressRepository.findAll(); 
    }

    // 2. TRIỂN KHAI SAVE
    @Override
    public CustomerAddress saveAddress(CustomerAddress customerAddress) {
        return customerAddressRepository.save(customerAddress);
    }

    // 3. TRIỂN KHAI GET BY ID
    @Override
    public CustomerAddress getAddressById(UUID id) {
        // Xử lý ngoại lệ nếu không tìm thấy
        return customerAddressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CustomerAddress not found with id: " + id));
    }

    // 4. TRIỂN KHAI GET BY CUSTOMER ID
    @Override
    public List<CustomerAddress> getAllAddressesByCustomerId(UUID customerId) {
        return customerAddressRepository.findByCustomerId(customerId);
    }

    // 5. TRIỂN KHAI DELETE
    @Override
    public void deleteAddress(UUID id) {
        customerAddressRepository.deleteById(id);
    }
}