package com.phamvantan.exercise201.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Bảng Nối (Join Table)
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_categories")
// Thêm để bỏ qua lỗi đệ quy của Jackson khi serialization
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class ProductCategory {

    // ✅ Sửa: ID tự động sinh (đáp ứng yêu cầu "+id (tự auto)")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    // Lưu ý: Đã bỏ @EmbeddedId và ProductCategoryID không còn cần thiết

    // ✅ Sửa: Dùng FetchType.EAGER để tải đầy đủ thông tin khi GET
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // ✅ Sửa: Dùng FetchType.EAGER để tải đầy đủ thông tin khi GET
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Bỏ constructor cũ vì không còn dùng Khóa phức hợp nữa.
    // Lombok (@NoArgsConstructor, @Getter, @Setter) đã đủ.
}