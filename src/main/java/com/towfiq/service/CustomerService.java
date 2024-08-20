package com.towfiq.service;

import com.towfiq.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerEntity> getAllCustomers();

    Optional<CustomerEntity> getCustomerId(Long id);

    CustomerEntity addCustomer(CustomerEntity customer);

    void saveOrUpdateCustomer(CustomerEntity customer);

    Optional<CustomerEntity> updateCustomer(Long id, CustomerEntity customer);

    void deleteCustomer(Long id);

    void deleteAllCustomers();
}
