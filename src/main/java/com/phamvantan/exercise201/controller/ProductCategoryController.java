package com.phamvantan.exercise201.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phamvantan.exercise201.entity.ProductCategory;
import com.phamvantan.exercise201.service.ProductCategoryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/category-products")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    // --- GET (Lấy tất cả quan hệ) ---
    // Bây giờ sẽ trả về 3 thành phần: ID (tự động), Product (đầy đủ), Category (đầy đủ)
    @GetMapping
    public ResponseEntity<List<ProductCategory>> getAll() {
        // Lưu ý: Đã đổi kiểu trả về từ Object sang List<ProductCategory>
        return ResponseEntity.ok(productCategoryService.findAll());
    }

    // --- POST (Thêm Product vào Category bằng JSON body) ---
    @PostMapping
    public ResponseEntity<ProductCategory> addProductToCategory(@RequestBody ProductCategory requestProductCategory) {
        // Trích xuất ID từ đối tượng nhận được
        UUID productId = requestProductCategory.getProduct().getId();
        UUID categoryId = requestProductCategory.getCategory().getId();

        // ✅ Sửa: Gọi phương thức Service mới để tạo và lưu mối quan hệ, 
        // trả về đối tượng đã được lưu (với ID tự động sinh)
        ProductCategory savedRelationship = 
            productCategoryService.createRelationship(productId, categoryId);
            
        return ResponseEntity.ok(savedRelationship);
    }
    
    // ✅ Lấy danh sách Category của 1 Product
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductCategory>> getCategoriesByProduct(@PathVariable UUID productId) {
        return ResponseEntity.ok(productCategoryService.getCategoriesByProduct(productId));
    }

    // ✅ Lấy danh sách Product thuộc 1 Category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductCategory>> getProductsByCategory(@PathVariable UUID categoryId) {
        return ResponseEntity.ok(productCategoryService.getProductsByCategory(categoryId));
    }

    // ✅ Xóa Product khỏi Category
    @DeleteMapping("/{productId}/{categoryId}")
    public ResponseEntity<String> removeProductFromCategory(@PathVariable UUID productId,
                                                           @PathVariable UUID categoryId) {
        productCategoryService.removeProductFromCategory(productId, categoryId);
        return ResponseEntity.ok("Product " + productId + " đã được xóa khỏi Category " + categoryId);
    }

    // ✅ Kiểm tra Product có trong Category hay không
    @GetMapping("/exists/{productId}/{categoryId}")
    public ResponseEntity<Boolean> existsInCategory(@PathVariable UUID productId,
                                                    @PathVariable UUID categoryId) {
        return ResponseEntity.ok(productCategoryService.existsProductInCategory(productId, categoryId));
    }
}