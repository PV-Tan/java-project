package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.UUID;

import com.phamvantan.exercise201.entity.Customer;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(UUID id);

    Customer create(Customer customer);

    Customer update(UUID id, Customer customer);

    void delete(UUID id);
}
