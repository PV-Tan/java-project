package com.phamvantan.exercise201.repository;

import com.phamvantan.exercise201.entity.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SellRepository extends JpaRepository<Sell, UUID> {
}