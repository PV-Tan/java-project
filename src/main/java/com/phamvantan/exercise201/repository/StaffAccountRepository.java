package com.phamvantan.exercise201.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phamvantan.exercise201.entity.StaffAccount;

import java.util.Optional;
import java.util.UUID;

public interface StaffAccountRepository extends JpaRepository<StaffAccount, UUID> {
    Optional<StaffAccount> findByEmail(String email);
    boolean existsByEmail(String email);
}
