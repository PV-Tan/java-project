package com.phamvantan.exercise201.controller;

import com.phamvantan.exercise201.entity.Sideshow;
import com.phamvantan.exercise201.repository.StaffAccountRepository; 
import com.phamvantan.exercise201.service.SideshowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/sideshows")
@RequiredArgsConstructor
public class SideshowController {
    private final SideshowService sideshowService;
    private final StaffAccountRepository staffAccountRepository;

    @GetMapping
    public List<Sideshow> getAll() {
        return sideshowService.findAll();
    }

    // Đã sửa lỗi: Tự kiểm tra null thay vì dùng ResponseEntity.of()
    @GetMapping("/{id}")
    public ResponseEntity<Sideshow> getById(@PathVariable UUID id) {
        Sideshow sideshow = sideshowService.findById(id);
        if (sideshow == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sideshow);
    }

    @PostMapping
    public ResponseEntity<Sideshow> create(@RequestBody Map<String, Object> payload) {
        Sideshow sideshow = mapPayload(new Sideshow(), payload, true);
        Sideshow saved = sideshowService.save(sideshow);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sideshow> update(@PathVariable UUID id, @RequestBody Map<String, Object> payload) {
        Sideshow sideshow = sideshowService.findById(id);
        if (sideshow == null) return ResponseEntity.notFound().build();

        sideshow = mapPayload(sideshow, payload, false);
        Sideshow updated = sideshowService.save(sideshow);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        sideshowService.deleteById(id);
        return ResponseEntity.ok("Sideshow successfully deleted!");
    }

    // ---------------- HELPER ----------------
    private Sideshow mapPayload(Sideshow sideshow, Map<String, Object> payload, boolean isCreate) {
        sideshow.setTitle((String) payload.get("title"));
        sideshow.setDestinationUrl((String) payload.get("destinationUrl"));
        
        // Kiểm tra NOT NULL: image
        String image = (String) payload.get("image");
        if (image == null) throw new IllegalArgumentException("Image is required (NOT NULL).");
        sideshow.setImage(image);

        // Kiểm tra NOT NULL: placeholder
        String placeholder = (String) payload.get("placeholder");
        if (placeholder == null) throw new IllegalArgumentException("Placeholder is required (NOT NULL).");
        sideshow.setPlaceholder(placeholder);
        
        sideshow.setDescription((String) payload.get("description"));
        sideshow.setBtnLabel((String) payload.get("btnLabel"));
        
        // Xử lý Integer NOT NULL: displayOrder
        Object displayOrderObj = payload.get("displayOrder");
        if (displayOrderObj instanceof Integer) {
             sideshow.setDisplayOrder((Integer) displayOrderObj);
        } else if (displayOrderObj != null) {
             try {
                // Xử lý trường hợp Postman gửi số nguyên dưới dạng Double
                if (displayOrderObj instanceof Double) {
                    sideshow.setDisplayOrder(((Double) displayOrderObj).intValue());
                } else {
                    sideshow.setDisplayOrder(Integer.parseInt(String.valueOf(displayOrderObj)));
                }
             } catch (Exception e) {
                throw new IllegalArgumentException("Display Order must be a valid integer.");
             }
        } else if (isCreate) {
             throw new IllegalArgumentException("Display Order is required (NOT NULL).");
        }


        sideshow.setPublished((Boolean) payload.getOrDefault("published", false));
        sideshow.setClicks((Integer) payload.getOrDefault("clicks", 0));
        sideshow.setStyles((String) payload.get("styles")); // Sử dụng String cho styles

        if (isCreate) sideshow.setCreatedAt(OffsetDateTime.now());
        sideshow.setUpdatedAt(OffsetDateTime.now());

        // createdBy
        Map<String, Object> createdByMap = (Map<String, Object>) payload.get("createdBy");
        if (createdByMap != null && createdByMap.get("id") instanceof String createdByIdStr) {
            UUID createdById = UUID.fromString(createdByIdStr);
            staffAccountRepository.findById(createdById).ifPresent(sideshow::setCreatedBy);
        } else if (isCreate) {
             sideshow.setCreatedBy(null); 
        }

        // updatedBy
        Map<String, Object> updatedByMap = (Map<String, Object>) payload.get("updatedBy");
        if (updatedByMap != null && updatedByMap.get("id") instanceof String updatedByIdStr) {
            UUID updatedById = UUID.fromString(updatedByIdStr);
            staffAccountRepository.findById(updatedById).ifPresent(sideshow::setUpdatedBy);
        } else if (!isCreate) {
            sideshow.setUpdatedBy(null);
        }

        return sideshow;
    }
}