package com.phamvantan.exercise201.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phamvantan.exercise201.entity.StaffAccount;
import com.phamvantan.exercise201.repository.RoleRepository;
import com.phamvantan.exercise201.repository.StaffAccountRepository;
import com.phamvantan.exercise201.service.StaffAccountService;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffAccountController {

    private final StaffAccountService staffAccountService;
    private final RoleRepository roleRepository;
    private final StaffAccountRepository staffAccountRepository;

    // ✅ READ ALL
    @GetMapping
    public List<StaffAccount> getAll() {
        return staffAccountService.findAll();
    }

    // ✅ READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<StaffAccount> getById(@PathVariable UUID id) {
        return ResponseEntity.of(staffAccountRepository.findById(id));
    }

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> payload) {
        String email = (String) payload.get("email");
        if (email != null && staffAccountRepository.existsByEmail(email)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already exists!"));
        }

        StaffAccount account = mapPayload(new StaffAccount(), payload, true);
        StaffAccount saved = staffAccountService.save(account);
        return ResponseEntity.ok(saved);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Map<String, Object> payload) {
        StaffAccount account = staffAccountService.findById(id);
        if (account == null) return ResponseEntity.notFound().build();

        String newEmail = (String) payload.get("email");
        if (newEmail != null) {
            staffAccountRepository.findByEmail(newEmail).ifPresent(existing -> {
                if (!existing.getId().equals(id)) {
                    throw new RuntimeException("Email already exists!");
                }
            });
        }

        account = mapPayload(account, payload, false);
        StaffAccount updated = staffAccountService.save(account);
        return ResponseEntity.ok(updated);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        staffAccountService.deleteById(id);
        return ResponseEntity.ok("Staff successfully deleted!");
    }

    // ---------------- HELPER ----------------
    private StaffAccount mapPayload(StaffAccount account, Map<String, Object> payload, boolean isCreate) {
        account.setFirstName((String) payload.get("firstName"));
        account.setLastName((String) payload.get("lastName"));
        account.setPhoneNumber((String) payload.get("phoneNumber"));
        account.setEmail((String) payload.get("email"));
        account.setPasswordHash((String) payload.get("passwordHash"));
        account.setActive((Boolean) payload.getOrDefault("active", true));
        account.setImage((String) payload.get("image"));
        account.setPlaceholder((String) payload.get("placeholder"));

        if (isCreate) account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());

        // Role
        Map<String, Object> roleMap = (Map<String, Object>) payload.get("role");
        if (roleMap != null && roleMap.get("id") instanceof String roleIdStr) {
            UUID roleId = UUID.fromString(roleIdStr);
            roleRepository.findById(roleId).ifPresent(account::setRole);
        }

        // createdBy
        Map<String, Object> createdByMap = (Map<String, Object>) payload.get("createdBy");
        if (createdByMap != null && createdByMap.get("id") instanceof String createdByIdStr) {
            UUID createdById = UUID.fromString(createdByIdStr);
            staffAccountRepository.findById(createdById).ifPresent(account::setCreatedBy);
        } else {
            account.setCreatedBy(null);
        }

        // updatedBy
        Map<String, Object> updatedByMap = (Map<String, Object>) payload.get("updatedBy");
        if (updatedByMap != null && updatedByMap.get("id") instanceof String updatedByIdStr) {
            UUID updatedById = UUID.fromString(updatedByIdStr);
            staffAccountRepository.findById(updatedById).ifPresent(account::setUpdatedBy);
        } else {
            account.setUpdatedBy(null);
        }

        return account;
    }
}
