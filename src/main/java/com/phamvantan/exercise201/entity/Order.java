package com.phamvantan.exercise201.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {

    @Id
    @Column(name = "id", length = 50, nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Coupon coupon;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setCouponId(UUID couponId) {
        if (couponId != null) {
            this.coupon = new Coupon();
            this.coupon.setId(couponId);
        }
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customer;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setCustomerId(UUID customerId) {
        if (customerId != null) {
            this.customer = new Customer();
            this.customer.setId(customerId);
        }
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_status_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private OrderStatus orderStatus;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setOrderStatusId(UUID orderStatusId) {
        if (orderStatusId != null) {
            this.orderStatus = new OrderStatus();
            this.orderStatus.setId(orderStatusId);
        }
    }

    @Column(name = "order_approved_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime orderApprovedAt;

    @Column(name = "order_delivered_carrier_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime orderDeliveredCarrierDate;

    @Column(name = "order_delivered_customer_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime orderDeliveredCustomerDate;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private StaffAccount updatedBy;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setUpdatedById(UUID updatedById) {
        if (updatedById != null) {
            this.updatedBy = new StaffAccount();
            this.updatedBy.setId(updatedById);
        }
    }
}
