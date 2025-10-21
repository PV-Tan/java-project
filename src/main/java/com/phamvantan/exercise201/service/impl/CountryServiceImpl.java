package com.phamvantan.exercise201.service.impl;

import org.springframework.stereotype.Service;

import com.phamvantan.exercise201.entity.Country;
import com.phamvantan.exercise201.repository.CountryRepository;
import com.phamvantan.exercise201.service.CountryService;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> getCountryById(Integer id) {
        return countryRepository.findById(id);
    }

    @Override
    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public void deleteCountry(Integer id) {
        countryRepository.deleteById(id);
    }

    @Override
    public List<Country> findCountriesByName(String name) {
        return countryRepository.findByName(name);
    }
}