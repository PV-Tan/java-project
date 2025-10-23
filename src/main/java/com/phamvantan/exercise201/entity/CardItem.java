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
@Builder
@Entity
@Table(name = "card_items")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CardItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Tham chiếu đến card
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false, referencedColumnName = "id")
    private Card card;

    @Transient
    private UUID cardId; // Dùng để nhận từ request

    // Tham chiếu đến product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
    private Product product;

    @Transient
    private UUID productId;

    @Column(nullable = false)
    private Integer quantity = 1;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice = BigDecimal.ZERO;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PrePersist
    @PreUpdate
    public void beforeSave() {
        if (cardId != null) this.card = Card.builder().id(cardId).build();
        if (productId != null) this.product = Product.builder().id(productId).build();
        this.updatedAt = LocalDateTime.now();
    }
}
