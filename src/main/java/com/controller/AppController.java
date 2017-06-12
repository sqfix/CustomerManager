package com.controller;

import com.model.Customer;
import com.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class AppController {

    @Autowired
    private CustomerService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void saveCustomer(@RequestBody Customer customer) {
        service.saveCustomer(customer);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void editCustomer(@RequestBody Customer customer) {
        service.editCustomer();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeCustomerById(@PathVariable int id) {
        service.removeById(id);
    }

    @RequestMapping(value = "/searchBy/{condition}", method = RequestMethod.GET)
    public Customer searchCustomers(@PathVariable String condition) {
        return service.searchCustomers(condition);
    }
}
