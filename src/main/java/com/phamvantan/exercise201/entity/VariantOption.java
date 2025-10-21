package com.phamvantan.exercise201.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "variant_options")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VariantOption {

    // id: UUID NOT NULL DEFAULT uuid_generate
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID")
    private UUID id;

    // product_id: UUID REFERENCES products(id) NOT NULL -> Mối quan hệ ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Product product;

    // image_id: UUID REFERENCES gallery(id) ON DELETE SET NULL -> Mối quan hệ ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id") // Mặc định nullable=true, phù hợp với ON DELETE SET NULL
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Gallery image;

    // title: TEXT NOT NULL
    @Column(name = "title", columnDefinition = "TEXT", nullable = false)
    private String title;

    // sale_price: NUMERIC NOT NULL DEFAULT 0
    @Column(name = "sale_price", nullable = false)
    private BigDecimal salePrice = BigDecimal.ZERO;

    // compare_price: NUMERIC DEFAULT 0
    @Column(name = "compare_price", nullable = false)
    private BigDecimal comparePrice = BigDecimal.ZERO;

    // buying_price: NUMERIC DEFAULT NULL
    @Column(name = "buying_price")
    private BigDecimal buyingPrice;

    // quantity: INTEGER NOT NULL DEFAULT 0
    @Column(name = "quantity", nullable = false)
    private Integer quantity = 0;

    // sku: VARCHAR(255)
    @Column(name = "sku", length = 255)
    private String sku;

    // active: BOOLEAN DEFAULT TRUE
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    /**
     * Lombok annotations:
     * @Getter, @Setter: Tạo getter/setter cho tất cả các fields.
     * @NoArgsConstructor, @AllArgsConstructor: Tạo constructors.
     * Chú ý: Không sử dụng @Data để tránh vấn đề với @EqualsAndHashCode và Lazy Loading trong JPA.
     */
}