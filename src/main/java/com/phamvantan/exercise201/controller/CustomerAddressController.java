package com.phamvantan.exercise201.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phamvantan.exercise201.entity.CustomerAddress;
import com.phamvantan.exercise201.service.CustomerAddressService;

import java.util.List;
import java.util.UUID;

// Đánh dấu là REST Controller
@RestController
// Định nghĩa base path cho các API
@RequestMapping("/api/customer-addresses")
@RequiredArgsConstructor
public class CustomerAddressController {

    private final CustomerAddressService customerAddressService;

    // GET /api/customer-addresses: Lấy TẤT CẢ địa chỉ
    // Đây là phương thức đã được bổ sung để sửa lỗi 'Request method GET is not supported' 
    @GetMapping
    public ResponseEntity<List<CustomerAddress>> getAllAddresses() {
        List<CustomerAddress> addresses = customerAddressService.getAllAddresses();
        return ResponseEntity.ok(addresses);
    }

    // POST /api/customer-addresses: Tạo địa chỉ mới
    @PostMapping
    public ResponseEntity<CustomerAddress> createAddress(@RequestBody CustomerAddress customerAddress) {
        CustomerAddress savedAddress = customerAddressService.saveAddress(customerAddress);
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }

    // GET /api/customer-addresses/{id}: Lấy một địa chỉ theo ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerAddress> getAddressById(@PathVariable UUID id) {
        CustomerAddress address = customerAddressService.getAddressById(id);
        return ResponseEntity.ok(address);
    }

    // GET /api/customer-addresses/customer/{customerId}: Lấy TẤT CẢ địa chỉ của một Customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CustomerAddress>> getAddressesByCustomerId(@PathVariable UUID customerId) {
        List<CustomerAddress> addresses = customerAddressService.getAllAddressesByCustomerId(customerId);
        return ResponseEntity.ok(addresses);
    }

    // DELETE /api/customer-addresses/{id}: Xóa một địa chỉ
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable UUID id) {
        customerAddressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}