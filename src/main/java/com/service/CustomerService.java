package com.service;


import com.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    void saveCustomer(Customer customer);

    Customer getCustomerById(int id) throws NullPointerException;

    Customer getCustomerByName(String name);

    void removeById(int id);
    //TODO: update method for customer's !!!!
}
