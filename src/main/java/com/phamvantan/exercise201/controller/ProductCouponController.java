package com.phamvantan.exercise201.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phamvantan.exercise201.entity.ProductCoupon;
import com.phamvantan.exercise201.service.ProductCouponService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/product-coupons")
public class ProductCouponController {

    private final ProductCouponService productCouponService;

    public ProductCouponController(ProductCouponService productCouponService) {
        this.productCouponService = productCouponService;
    }

    @PostMapping
    public ResponseEntity<ProductCoupon> createProductCoupon(@RequestBody ProductCoupon productCoupon) {
        ProductCoupon createdCoupon = productCouponService.createProductCoupon(productCoupon);
        // Sau khi lưu, đối tượng `createdCoupon` sẽ chứa `product` với `createdBy/updatedBy` bị ẩn (nhờ @JsonIgnoreProperties trong Entity Product)
        return new ResponseEntity<>(createdCoupon, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCoupon> getProductCouponById(@PathVariable UUID id) {
        try {
            ProductCoupon productCoupon = productCouponService.getProductCouponById(id);
            return ResponseEntity.ok(productCoupon);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductCoupon>> getAllProductCoupons() {
        List<ProductCoupon> productCoupons = productCouponService.getAllProductCoupons();
        return ResponseEntity.ok(productCoupons);
    }
}