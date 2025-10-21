package com.phamvantan.exercise201.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // TEXT NOT NULL UNIQUE
    @Column(name = "slug", columnDefinition = "TEXT", nullable = false, unique = true)
    private String slug;

    // VARCHAR(255) NOT NULL
    @Column(name = "product_name", nullable = false, length = 255)
    private String productName;

    // VARCHAR(255)
    @Column(name = "sku", length = 255)
    private String sku;

    // NUMERIC NOT NULL DEFAULT 0 (Dùng BigDecimal cho tiền tệ)
    @Column(name = "sale_price", nullable = false)
    private BigDecimal salePrice = BigDecimal.ZERO;

    // NUMERIC DEFAULT 0
    // CHECK (compare_price >= sale_price OR compare_price = 0) - Ràng buộc này được xử lý ở tầng Service/DB
    @Column(name = "compare_price", nullable = false)
    private BigDecimal comparePrice = BigDecimal.ZERO;

    // NUMERIC DEFAULT NULL
    @Column(name = "buying_price")
    private BigDecimal buyingPrice;

    // INTEGER NOT NULL DEFAULT 0
    @Column(name = "quantity", nullable = false)
    private Integer quantity = 0;

    // VARCHAR(165)
    @Column(name = "short_description", length = 165)
    private String shortDescription;

    // TEXT NOT NULL
    @Column(name = "product_description", columnDefinition = "TEXT", nullable = false)
    private String productDescription;

    // VARCHAR(64) CHECK (product_type IN ('simple', 'variable')) - Cần validation ở Service
    // Nên dùng Enum, nhưng dùng String theo yêu cầu
    @Column(name = "product_type", length = 64)
    private String productType;

    // BOOLEAN DEFAULT FALSE
    @Column(name = "published", nullable = false)
    private Boolean published = false;

    // BOOLEAN DEFAULT TRUE
    @Column(name = "disable_out_of_stock", nullable = false)
    private Boolean disableOutOfStock = true;

    // TEXT
    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    // TIMESTAMPTZ NOT NULL DEFAULT NOW()
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // TIMESTAMPTZ NOT NULL DEFAULT NOW()
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    // UUID REFERENCES staff_accounts(id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @JsonIgnoreProperties({"createdBy", "updatedBy", "hibernateLazyInitializer", "handler"})
    private StaffAccount createdBy;

    // UUID REFERENCES staff_accounts(id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    @JsonIgnoreProperties({"createdBy", "updatedBy", "hibernateLazyInitializer", "handler"})
    private StaffAccount updatedBy;
}
