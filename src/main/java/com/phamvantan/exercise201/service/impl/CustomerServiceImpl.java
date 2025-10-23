package com.phamvantan.exercise201.service.impl;

import com.phamvantan.exercise201.entity.Customer;
import com.phamvantan.exercise201.repository.CustomerRepository;
import com.phamvantan.exercise201.service.CustomerService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private Customer getExistingCustomer(UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(UUID id) {
        return getExistingCustomer(id);
    }

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(UUID id, Customer customerDetails) {
        Customer existing = getExistingCustomer(id);

        // KHÔI PHỤC CÁC LOGIC UPDATE CŨ CỦA BẠN TẠI ĐÂY
        // Ví dụ:
        // if (customerDetails.getFirstName() != null) {
        //     existing.setFirstName(customerDetails.getFirstName());
        // }
        // if (customerDetails.getEmail() != null) {
        //     existing.setEmail(customerDetails.getEmail());
        // }
        
        return customerRepository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        Customer existing = getExistingCustomer(id);
        customerRepository.delete(existing);
    }
}