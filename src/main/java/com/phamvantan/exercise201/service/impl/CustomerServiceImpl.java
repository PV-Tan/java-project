package com.phamvantan.exercise201.service.impl;

import org.springframework.stereotype.Service;

import com.phamvantan.exercise201.entity.Customer;
import com.phamvantan.exercise201.repository.CustomerRepository;
import com.phamvantan.exercise201.service.CustomerService;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @Override
    public Customer create(Customer customer) {
        // Kiểm tra trùng email
        customerRepository.findByEmail(customer.getEmail()).ifPresent(c -> {
            throw new RuntimeException("Email already exists: " + customer.getEmail());
        });
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(UUID id, Customer customer) {
        Customer existing = findById(id);

        existing.setFirstName(customer.getFirstName());
        existing.setLastName(customer.getLastName());
        existing.setEmail(customer.getEmail());
        existing.setPasswordHash(customer.getPasswordHash());
        existing.setActive(customer.getActive());

        return customerRepository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }
}
