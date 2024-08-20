package com.towfiq.service.impl;

import com.towfiq.entity.CustomerEntity;
import com.towfiq.repository.CustomerRepository;
import com.towfiq.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<CustomerEntity> getCustomerId(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public CustomerEntity addCustomer(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void saveOrUpdateCustomer(CustomerEntity customer) {
        Optional<CustomerEntity> existingCustomerEntity = customerRepository.findByEmail(customer.getEmail());

        if (existingCustomerEntity.isPresent()) {
            CustomerEntity updatedCustomerEntity = existingCustomerEntity.get();
            updatedCustomerEntity.setName(customer.getName());
            updatedCustomerEntity.setAddress(customer.getAddress());
            customerRepository.save(updatedCustomerEntity);
        } else {
            customerRepository.save(customer);
        }
    }

    @Override
    public Optional<CustomerEntity> updateCustomer(Long id, CustomerEntity customer) {
        Optional<CustomerEntity> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            CustomerEntity updatedCustomer = existingCustomer.get();
            updatedCustomer.setName(customer.getName());
            updatedCustomer.setPhone(customer.getPhone());
            updatedCustomer.setEmail(customer.getEmail());
            updatedCustomer.setPosition(customer.getPosition());
            updatedCustomer.setBio(customer.getBio());
            customerRepository.save(updatedCustomer);
        }
        return existingCustomer;
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }

}
