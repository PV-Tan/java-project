package com.phamvantan.exercise201.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter; // 👈 ĐÃ THÊM!
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

// Bắt buộc phải implements Serializable, có NoArgsConstructor, AllArgsConstructor, và EqualsAndHashCode
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter // 👈 Lombok tự động tạo getter hoạt động đúng
public class ProductCategoryID implements Serializable {

    private static final long serialVersionUID = 1L;

    // UUID REFERENCES products(id) NOT NULL
    private UUID productId;

    // UUID REFERENCES categories(id) NOT NULL
    private UUID categoryId;

    // Đã xóa các phương thức getProductId() và getCategoryId() bị lỗi.
    // Lombok sẽ tạo chúng.
}