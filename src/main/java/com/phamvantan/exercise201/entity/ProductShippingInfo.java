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
@Table(name = "product_shipping_info")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductShippingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = true)
    private Product product;

    @Column(nullable = false)
    private BigDecimal weight = BigDecimal.ZERO;

    @Column(name = "weight_unit", length = 10)
    private String weightUnit;

    @Column(nullable = false)
    private BigDecimal volume = BigDecimal.ZERO;

    @Column(name = "volume_unit", length = 10)
    private String volumeUnit;

    @Column(name = "dimension_width", nullable = false)
    private BigDecimal dimensionWidth = BigDecimal.ZERO;

    @Column(name = "dimension_height", nullable = false)
    private BigDecimal dimensionHeight = BigDecimal.ZERO;

    @Column(name = "dimension_depth", nullable = false)
    private BigDecimal dimensionDepth = BigDecimal.ZERO;

    @Column(name = "dimension_unit", length = 10)
    private String dimensionUnit;
}
