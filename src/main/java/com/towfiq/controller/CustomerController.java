package com.towfiq.controller;

import com.towfiq.entity.CustomerEntity;
import com.towfiq.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(path = "/")
    public String index() {
        return "external";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.logout();
        return "redirect:/";
    }

    @GetMapping(path = "/customers")
    public String customers(Principal principal, Model model) {
        addCustomers();
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("username", principal.getName());
        return "customers";
    }

    @GetMapping(path = "/api/customers")
    public ResponseEntity<?> customersApi(Principal principal, Model model) {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    // add customers for demonstration
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
