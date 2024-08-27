package com.example.BookstoreAPI.controller;

import com.example.BookstoreAPI.dto.CustomerDTO;
import com.example.BookstoreAPI.model.Customer;
import com.example.BookstoreAPI.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers().stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        CustomerDTO customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(modelMapper.map(customer, CustomerDTO.class));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Validated @RequestBody CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(modelMapper.map(createdCustomer, CustomerDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @Validated @RequestBody CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(modelMapper.map(updatedCustomer, CustomerDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}