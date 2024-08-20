package com.towfiq.component;

import com.towfiq.entity.CustomerEntity;
import com.towfiq.service.CustomerService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private final CustomerService customerService;

    public StartupApplicationListener(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        addCustomers();
    }

    private void addCustomers() {
        CustomerEntity customer1 = new CustomerEntity();
        customer1.setAddress("1111 foo blvd");
        customer1.setName("Foo Industries");
        customer1.setEmail("test1@isquare.digital");
        customer1.setServiceRendered("Important services");
        customerService.saveOrUpdateCustomer(customer1);

        CustomerEntity customer2 = new CustomerEntity();
        customer2.setAddress("2222 bar street");
        customer2.setName("Bar LLP");
        customer2.setEmail("test2@isquare.digital");
        customer2.setServiceRendered("Important services");
        customerService.saveOrUpdateCustomer(customer2);

        CustomerEntity customer3 = new CustomerEntity();
        customer3.setAddress("33 main street");
        customer3.setName("Big LLC");
        customer3.setEmail("test3@isquare.digital");
        customer3.setServiceRendered("Important services");
        customerService.saveOrUpdateCustomer(customer3);
    }
}
