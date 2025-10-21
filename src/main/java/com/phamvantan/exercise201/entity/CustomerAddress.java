package com.phamvantan.exercise201.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

// Sử dụng Lombok để tự động tạo getter, setter, constructor, và builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Đánh dấu đây là một Entity
@Entity
// Ánh xạ tới bảng 'customer_addresses'
@Table(name = "customer_addresses")
public class CustomerAddress {

    // ID tự động sinh UUID cho bảng customer_addresses
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Liên kết Many-to-One: Nhiều địa chỉ thuộc về một Customer.
    // Điều này đáp ứng yêu cầu liệt kê hết thông tin Customer khi nhập customer_id 
    @ManyToOne(fetch = FetchType.LAZY) // Tải lười (LAZY) để tránh tải Customer khi không cần thiết
    @JoinColumn(name = "customer_id", nullable = false) // Ánh xạ tới cột 'customer_id' trong DB 
    private Customer customer;

    // Địa chỉ dòng 1: TEXT NOT NULL 
    @Column(name = "address_line1", columnDefinition = "TEXT", nullable = false)
    private String addressLine1; // Sử dụng camelCase để dễ nhập Postman 

    // Địa chỉ dòng 2: TEXT 
    @Column(name = "address_line2", columnDefinition = "TEXT")
    private String addressLine2; // Sử dụng camelCase để dễ nhập Postman 

    // Số điện thoại: VARCHAR(255) NOT NULL 
    @Column(name = "phone_number", length = 255, nullable = false)
    private String phoneNumber; // Sử dụng camelCase để dễ nhập Postman 

    // Mã vùng điện thoại: VARCHAR(100) NOT NULL 
    @Column(name = "dial_code", length = 100, nullable = false)
    private String dialCode; // Sử dụng camelCase để dễ nhập Postman 

    // Quốc gia: VARCHAR(255) NOT NULL 
    @Column(name = "country", length = 255, nullable = false)
    private String country;

    // Mã bưu điện: VARCHAR(255) NOT NULL 
    @Column(name = "postal_code", length = 255, nullable = false)
    private String postalCode; // Sử dụng camelCase để dễ nhập Postman 

    // Thành phố: VARCHAR(255) NOT NULL 
    @Column(name = "city", length = 255, nullable = false)
    private String city;
}