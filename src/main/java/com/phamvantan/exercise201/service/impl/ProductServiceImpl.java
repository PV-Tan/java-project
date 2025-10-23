package com.phamvantan.exercise201.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phamvantan.exercise201.entity.Product;
import com.phamvantan.exercise201.repository.ProductRepository;
import com.phamvantan.exercise201.repository.StaffAccountRepository;
import com.phamvantan.exercise201.service.ProductService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StaffAccountRepository staffAccountRepository;

    private static final List<String> VALID_PRODUCT_TYPES = Arrays.asList("simple", "variable");

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        // Validation cho CHECK constraint: (compare_price >= sale_price OR compare_price = 0)
        if (product.getComparePrice() != null && product.getSalePrice() != null &&
            product.getComparePrice().compareTo(product.getSalePrice()) < 0 &&
            product.getComparePrice().compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException("Compare price must be greater than or equal to sale price, or be 0.");
        }
        if (!VALID_PRODUCT_TYPES.contains(product.getProductType())) {
            throw new IllegalArgumentException("Product type must be 'simple' or 'variable'.");
        }

        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product update(UUID id, Map<String, Object> payload) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // Áp dụng logic ánh xạ
        product = mapPayload(product, payload, false);

        return save(product); // Gọi save để tái sử dụng logic validation
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }

    // --- Helper function để ánh xạ Map payload vào Entity, KHÔNG DÙNG DTO ---
    private Product mapPayload(Product product, Map<String, Object> payload, boolean isCreate) {
        // Cập nhật các trường thông thường
        product.setSlug((String) payload.get("slug"));
        product.setProductName((String) payload.get("product_name"));
        product.setSku((String) payload.get("sku"));
        product.setShortDescription((String) payload.get("short_description"));
        product.setProductDescription((String) payload.get("product_description"));
        product.setProductType((String) payload.get("product_type"));
        product.setNote((String) payload.get("note"));

        // Cập nhật các trường số (cần parse)
        product.setSalePrice(new BigDecimal(String.valueOf(payload.getOrDefault("sale_price", BigDecimal.ZERO))));
        product.setComparePrice(new BigDecimal(String.valueOf(payload.getOrDefault("compare_price", BigDecimal.ZERO))));
        if (payload.containsKey("buying_price") && payload.get("buying_price") != null) {
            product.setBuyingPrice(new BigDecimal(String.valueOf(payload.get("buying_price"))));
        }
        product.setQuantity((Integer) payload.getOrDefault("quantity", 0));

        // Cập nhật các trường boolean
        product.setPublished((Boolean) payload.getOrDefault("published", false));
        product.setDisableOutOfStock((Boolean) payload.getOrDefault("disable_out_of_stock", true));

        // Cập nhật thời gian
        if (isCreate) product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        // Cập nhật createdBy (Tham chiếu StaffAccount)
        Map<String, Object> createdByMap = (Map<String, Object>) payload.get("createdBy");
        if (createdByMap != null && createdByMap.get("id") instanceof String createdByIdStr) {
            UUID createdById = UUID.fromString(createdByIdStr);
            staffAccountRepository.findById(createdById).ifPresent(product::setCreatedBy);
        } else {
            product.setCreatedBy(null);
        }

        // Cập nhật updatedBy (Tham chiếu StaffAccount)
        Map<String, Object> updatedByMap = (Map<String, Object>) payload.get("updatedBy");
        if (updatedByMap != null && updatedByMap.get("id") instanceof String updatedByIdStr) {
            UUID updatedById = UUID.fromString(updatedByIdStr);
            staffAccountRepository.findById(updatedById).ifPresent(product::setUpdatedBy);
        } else {
            product.setUpdatedBy(null);
        }

        return product;
    }
}
