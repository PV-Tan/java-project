package com.phamvantan.exercise201.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
@Entity
@Table(name = "variants")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Variant {

    // id: UUID NOT NULL DEFAULT uuid_generate
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID")
    private UUID id;

    // variant_option: TEXT NOT NULL (Giá trị của biến thể, ví dụ: "Đỏ", "Lớn")
    @Column(name = "variant_option", columnDefinition = "TEXT", nullable = false)
    private String variantOption;

    // product_id: UUID REFERENCES products(id) NOT NULL -> Mối quan hệ ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Product product;

    // variant_option_id: UUID REFERENCES variant_options(id) NOT NULL -> Mối quan hệ ManyToOne
    // Sử dụng tên variantOptionRef để tránh xung đột với cột TEXT "variant_option"
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_option_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private VariantOption variantOptionRef;

    /**
     * Lombok annotations:
     * @Getter, @Setter, @NoArgsConstructor, @AllArgsConstructor được sử dụng
     * theo đúng cấu trúc đã cung cấp cho các entities khác[cite: 17, 18, 19, 20].
     */
}