package com.phamvantan.exercise201.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamvantan.exercise201.entity.CustomerAddress;

import java.util.List;
import java.util.UUID;

// CustomerAddress là Entity, UUID là kiểu dữ liệu của ID
@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, UUID> {

    // Phương thức tự động tạo (Derived Query) để lấy tất cả địa chỉ theo Customer ID
    List<CustomerAddress> findByCustomerId(UUID customerId);
}