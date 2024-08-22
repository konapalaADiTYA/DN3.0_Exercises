package com.example.BookstoreAPI.controller;

import com.example.BookstoreAPI.model.Customer;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>();

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        customer.setId((long) (customers.size() + 1)); 
        customers.add(customer);
        return customer;
    }
    @PostMapping("/register")
    public Customer registerCustomer(@RequestParam String firstName,
                                     @RequestParam String lastName,
                                     @RequestParam String email) {
        Customer customer = new Customer();
        customer.setId((long) (customers.size() + 1)); 
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customers.add(customer);
        return customer;
    }
}
