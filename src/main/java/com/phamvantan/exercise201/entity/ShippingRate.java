package com.phamvantan.exercise201.entity; // Adjust package as needed

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
@Table(name = "shipping_rates")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ShippingRate {

    // id: UUID NOT NULL DEFAULT uuid_generate, auto-generated as requested [cite: 10, 11, 12]
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // shipping_zone_id: INTEGER REFERENCES shipping_zones(id) NOT NULL
    // Implemented as ManyToOne relationship to ShippingZone, and fetches full zone info 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_zone_id", referencedColumnName = "id", nullable = false)
    private ShippingZone shippingZone; // Includes full ShippingZone object

    // weight_unit: VARCHAR(10) CHECK (weight_unit IN ('g', 'kg'))
    @Column(name = "weight_unit", length = 10, nullable = false)
    private String weightUnit;

    // min_value: NUMERIC NOT NULL DEFAULT 0
    @Column(name = "min_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal minValue = BigDecimal.ZERO;

    // max_value: NUMERIC DEFAULT NULL CHECK (max_value > min_value OR no_max IS TRUE)
    @Column(name = "max_value", precision = 10, scale = 2)
    private BigDecimal maxValue;

    // no_max: BOOLEAN DEFAULT TRUE
    @Column(name = "no_max", nullable = false)
    private Boolean noMax = Boolean.TRUE;

    // price: NUMERIC NOT NULL DEFAULT 0
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price = BigDecimal.ZERO;

    // Note: The database CHECK constraints on weight_unit and max_value should ideally be enforced
    // at the database level, but application-level validation is also recommended.
}
// Note: Assumes ShippingZone class is available in the same package (com.ngodinhhung.exercise201.entity) as per the provided context[cite: 14].