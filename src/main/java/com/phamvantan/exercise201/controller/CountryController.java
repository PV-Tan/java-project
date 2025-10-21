package com.phamvantan.exercise201.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phamvantan.exercise201.entity.Country;
import com.phamvantan.exercise201.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    // Spring tự động tiêm CountryService vì nó là một Spring Bean
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAll() {
        return countryService.getAllCountries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getById(@PathVariable Integer id) {
        return countryService.getCountryById(id)
                .map(country -> new ResponseEntity<>(country, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping("/name/{name}")
    public List<Country> getByName(@PathVariable String name) {
        return countryService.findCountriesByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Country createCountry(@RequestBody Country country) {
        return countryService.saveCountry(country);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable Integer id, @RequestBody Country countryDetails) {
        return countryService.getCountryById(id)
                .map(existingCountry -> {
                    // Update fields of the existing entity
                    existingCountry.setIso(countryDetails.getIso());
                    existingCountry.setName(countryDetails.getName());
                    existingCountry.setUpperName(countryDetails.getUpperName());
                    existingCountry.setIso3(countryDetails.getIso3());
                    existingCountry.setNumCode(countryDetails.getNumCode());
                    existingCountry.setPhoneCode(countryDetails.getPhoneCode());

                    Country updatedCountry = countryService.saveCountry(existingCountry);
                    return new ResponseEntity<>(updatedCountry, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Integer id) {
        if (countryService.getCountryById(id).isPresent()) {
            countryService.deleteCountry(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}