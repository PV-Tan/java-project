package com.phamvantan.exercise201.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.phamvantan.exercise201.entity.*;
import com.phamvantan.exercise201.repository.*;
import com.phamvantan.exercise201.service.SupplierService;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;
    private final SupplierRepository supplierRepository;
    private final CountryRepository countryRepository;
    private final StaffAccountRepository staffAccountRepository;

    // ✅ READ ALL
    @GetMapping
    public List<Supplier> getAll() {
        return supplierService.findAll();
    }

    // ✅ READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getById(@PathVariable UUID id) {
        return ResponseEntity.of(supplierRepository.findById(id));
    }

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> payload) {
        String supplierName = (String) payload.get("supplier_name");
        if (supplierRepository.existsBySupplier_name(supplierName)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Supplier name already exists!"));
        }

        Supplier supplier = mapPayload(new Supplier(), payload, true);
        Supplier saved = supplierService.save(supplier);
        return ResponseEntity.ok(saved);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Map<String, Object> payload) {
        Supplier supplier = supplierService.findById(id);
        if (supplier == null) return ResponseEntity.notFound().build();

        supplier = mapPayload(supplier, payload, false);
        Supplier updated = supplierService.save(supplier);
        return ResponseEntity.ok(updated);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        supplierService.deleteById(id);
        return ResponseEntity.ok("Supplier deleted successfully!");
    }

    // ---------------- HELPER ----------------
    private Supplier mapPayload(Supplier supplier, Map<String, Object> payload, boolean isCreate) {
        supplier.setSupplier_name((String) payload.get("supplier_name"));
        supplier.setContact_name((String) payload.get("contact_name"));
        supplier.setContact_title((String) payload.get("contact_title"));
        supplier.setAddress((String) payload.get("address"));
        supplier.setCity((String) payload.get("city"));
        supplier.setPhone((String) payload.get("phone"));
        supplier.setFax((String) payload.get("fax"));
        supplier.setHomepage((String) payload.get("homepage"));

        if (isCreate) supplier.setCreated_at(LocalDateTime.now());
        supplier.setUpdated_at(LocalDateTime.now());

        // Country
        Map<String, Object> countryMap = (Map<String, Object>) payload.get("country");
        if (countryMap != null && countryMap.get("id") instanceof Number idNum) {
            Integer countryId = idNum.intValue();
            countryRepository.findById(countryId).ifPresent(supplier::setCountry);
        }

        // created_by
        Map<String, Object> createdByMap = (Map<String, Object>) payload.get("created_by");
        if (createdByMap != null && createdByMap.get("id") instanceof String createdByIdStr) {
            UUID createdById = UUID.fromString(createdByIdStr);
            staffAccountRepository.findById(createdById).ifPresent(supplier::setCreated_by);
        }

        // updated_by
        Map<String, Object> updatedByMap = (Map<String, Object>) payload.get("updated_by");
        if (updatedByMap != null && updatedByMap.get("id") instanceof String updatedByIdStr) {
            UUID updatedById = UUID.fromString(updatedByIdStr);
            staffAccountRepository.findById(updatedById).ifPresent(supplier::setUpdated_by);
        }

        return supplier;
    }
}
