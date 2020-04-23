package com.Seweryn91.RESTFlyway.controller;

import com.Seweryn91.RESTFlyway.exceptions.CustomerNotFoundException;
import com.Seweryn91.RESTFlyway.interfaces.CustomerRepository;
import com.Seweryn91.RESTFlyway.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerRepository repository;

    CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/customers")
    public List<Customer> allCustomers() {
        return (List<Customer>) repository.findAll();
    }

    @PostMapping("/customers")
    Customer newCustomer(@RequestBody Customer newCustomer) {
        return repository.save(newCustomer);
    }

    @GetMapping("/customers/{id}")
    Customer oneCustomer(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @PutMapping("/customers/{id}")
    Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {
        return repository.findById(id).map(customer -> {
            customer.setFirst_name(newCustomer.getFirst_name());
            customer.setLast_name(newCustomer.getLast_name());
            customer.setEmail(newCustomer.getEmail());
            customer.setPhone(newCustomer.getPhone());
            customer.setCountry(newCustomer.getCountry());
            customer.setCity(newCustomer.getCity());
            customer.setZipcode(newCustomer.getZipcode());
            customer.setStreet_number(newCustomer.getStreet_number());
            return repository.save(customer);
        }).orElseGet(() -> {
            newCustomer.setID(id);
            return repository.save(newCustomer);
        });
    }

    @DeleteMapping("/customers/{id}")
    void deleteCustomer(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
