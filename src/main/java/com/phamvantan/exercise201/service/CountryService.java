package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.Optional;

import com.phamvantan.exercise201.entity.Country;

public interface CountryService {

    // Retrieve all countries
    List<Country> getAllCountries();

    // Retrieve a country by ID
    Optional<Country> getCountryById(Integer id);

    // Save or update a country
    Country saveCountry(Country country);

    // Delete a country by ID
    void deleteCountry(Integer id);

    // Find countries by name
    List<Country> findCountriesByName(String name);
}