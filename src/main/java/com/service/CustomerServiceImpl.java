package com.service;


import com.controller.exceptions.CustomerNotFoundException;
import com.model.Customer;
import com.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository repository;

    @Override
    public List<Customer> getAllCustomers() {
        LOGGER.info("getAllCustomers");
        return repository.findAll();
    }

    @Override
    public void saveCustomer(Customer customer) {
        LOGGER.info("saveCustomer " + customer);
        repository.save(customer);
    }

    @Override
    public Customer getCustomerById(int id) {
        LOGGER.info("getCustomerById " + id);
        return repository.findById(id);
    }

    @Override
    public Customer getCustomerByName(String name) {
        LOGGER.info("getCustomerByName " + name);
        return repository.findByName(name);
    }

    @Override
    public void removeById(int id) {
        LOGGER.info("removeById " + id);
        repository.removeById(id);
    }

    @Override
    public void editCustomer() {
        LOGGER.info("update Customer");
    }

    @Override
    public Customer searchCustomers(String condition) {
        LOGGER.info("searchCustomers " + condition);
        try {
            int id = Integer.parseInt(condition);
            Customer customer = getCustomerById(id);
            if (customer == null)
                throw new CustomerNotFoundException();
            return customer;
        } catch (Exception e) {
            Customer customer = getCustomerByName(condition);
            if (customer == null)
                throw new CustomerNotFoundException();
            return customer;
        }
    }
}
