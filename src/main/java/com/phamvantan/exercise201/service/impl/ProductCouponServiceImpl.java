package com.phamvantan.exercise201.service.impl;

import org.springframework.stereotype.Service;

import com.phamvantan.exercise201.entity.ProductCoupon;
import com.phamvantan.exercise201.repository.ProductCouponRepository;
import com.phamvantan.exercise201.service.ProductCouponService;

import java.util.List;
import java.util.UUID;
import java.util.NoSuchElementException;

@Service
public class ProductCouponServiceImpl implements ProductCouponService {

    private final ProductCouponRepository productCouponRepository;

    public ProductCouponServiceImpl(ProductCouponRepository productCouponRepository) {
        this.productCouponRepository = productCouponRepository;
    }

    @Override
    public ProductCoupon createProductCoupon(ProductCoupon productCoupon) {
        // Có thể thêm logic kiểm tra ràng buộc (ví dụ: product_id và coupon_id tồn tại)
        return productCouponRepository.save(productCoupon);
    }

    @Override
    public ProductCoupon getProductCouponById(UUID id) {
        // Yêu cầu: Liệt kê đầy đủ thông tin của Product (đã tải qua EAGER và ẩn createdBy/updatedBy qua @JsonIgnoreProperties trong Product Entity)
        return productCouponRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ProductCoupon not found with id: " + id));
    }

    @Override
    public List<ProductCoupon> getAllProductCoupons() {
        return productCouponRepository.findAll();
    }
}