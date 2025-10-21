package com.phamvantan.exercise201.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_coupons")
public class ProductCoupon {

    // Yêu cầu: ID tự động sinh UUID
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Liên kết với bảng products (product_id)
    // Tải thông tin Product đầy đủ khi tải ProductCoupon
    // Cần đảm bảo Entity Product được cấu hình đúng để ẩn createdBy/updatedBy (đã có trong gợi ý Product.java)
    @ManyToOne(fetch = FetchType.EAGER) // EAGER để tải Product đầy đủ ngay lập tức
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Liên kết với bảng coupons (coupon_id)
    @ManyToOne(fetch = FetchType.EAGER) // LAZY là mặc định tốt hơn cho các mối quan hệ không ưu tiên
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;
}