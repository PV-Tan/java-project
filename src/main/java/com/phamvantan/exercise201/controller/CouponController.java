package com.phamvantan.exercise201.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phamvantan.exercise201.entity.Coupon;
import com.phamvantan.exercise201.service.CouponService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    // POST /api/coupons: Tạo coupon mới
    @PostMapping
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
        Coupon savedCoupon = couponService.saveCoupon(coupon);
        return new ResponseEntity<>(savedCoupon, HttpStatus.CREATED);
    }

    // GET /api/coupons: Lấy tất cả coupons
    @GetMapping
    public ResponseEntity<List<Coupon>> getAllCoupons() {
        List<Coupon> coupons = couponService.getAllCoupons();
        return ResponseEntity.ok(coupons);
    }

    // GET /api/coupons/{id}: Lấy một coupon theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Coupon> getCouponById(@PathVariable UUID id) {
        Coupon coupon = couponService.getCouponById(id);
        return ResponseEntity.ok(coupon);
    }

    // GET /api/coupons/code/{code}: Lấy một coupon theo code
    @GetMapping("/code/{code}")
    public ResponseEntity<Coupon> getCouponByCode(@PathVariable String code) {
        Coupon coupon = couponService.getCouponByCode(code);
        return ResponseEntity.ok(coupon);
    }
    
    // PUT /api/coupons/{id}: Cập nhật coupon
    @PutMapping("/{id}")
    public ResponseEntity<Coupon> updateCoupon(@PathVariable UUID id, @RequestBody Coupon couponDetails) {
        // Lấy coupon cũ để cập nhật (giữ nguyên ID và times_used)
        Coupon existingCoupon = couponService.getCouponById(id);
        
        // Cập nhật các trường (trừ timesUsed)
        existingCoupon.setCode(couponDetails.getCode());
        existingCoupon.setDiscountValue(couponDetails.getDiscountValue());
        existingCoupon.setDiscountType(couponDetails.getDiscountType());
        existingCoupon.setMaxUsage(couponDetails.getMaxUsage());
        existingCoupon.setOrderAmountLimit(couponDetails.getOrderAmountLimit());
        existingCoupon.setCouponStartDate(couponDetails.getCouponStartDate());
        existingCoupon.setCouponEndDate(couponDetails.getCouponEndDate());
        
        Coupon updatedCoupon = couponService.saveCoupon(existingCoupon);
        return ResponseEntity.ok(updatedCoupon);
    }

    // DELETE /api/coupons/{id}: Xóa một coupon
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable UUID id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.noContent().build();
    }
}