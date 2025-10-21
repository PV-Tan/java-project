package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.UUID;

import com.phamvantan.exercise201.entity.CustomerAddress;

public interface CustomerAddressService {
    
    // 1. Lấy tất cả địa chỉ (cho GET /api/customer-addresses)
    List<CustomerAddress> getAllAddresses(); 

    // 2. Lưu/Cập nhật một địa chỉ
    CustomerAddress saveAddress(CustomerAddress customerAddress);

    // 3. Lấy một địa chỉ theo ID (cho GET /api/customer-addresses/{id})
    CustomerAddress getAddressById(UUID id);

    // 4. Lấy tất cả địa chỉ của một Customer (cho GET /api/customer-addresses/customer/{customerId})
    List<CustomerAddress> getAllAddressesByCustomerId(UUID customerId);

    // 5. Xóa một địa chỉ theo ID
    void deleteAddress(UUID id);
}