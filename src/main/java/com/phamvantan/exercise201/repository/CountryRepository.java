package com.phamvantan.exercise201.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamvantan.exercise201.entity.Country;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    
    // Custom query method example: Find countries by name
    List<Country> findByName(String name);
}