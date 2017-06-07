package com.controller;

import com.controller.exceptions.CustomerNotFoundException;
import com.model.Customer;
import com.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private CustomerService service;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", service.getAllCustomers());

        return "listOfCustomers";
    }

    @RequestMapping(value = "saveCustomer", method = RequestMethod.GET)
    public String saveCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "editCustomer";
    }


    @RequestMapping(value = "saveCustomer", method = RequestMethod.POST)
    public String saveCustomer(Model model,
                               @ModelAttribute(value = "customer") Customer customer) {
        service.saveCustomer(customer);
        return getAllCustomers(model);
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String editCustomer(@PathVariable int id, Model model) {
        Customer customer = service.getCustomerById(id);
        if (customer == null)
            throw new CustomerNotFoundException();
        model.addAttribute("customer", customer);
        return "editCustomer";
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public String getById(Model model, @RequestParam(name = "id") int id) {
        model.addAttribute("customers", service.getCustomerById(id));
        return "listOfCustomers";
    }

    @RequestMapping(value = "/getByName", method = RequestMethod.GET)
    public String getByName(Model model, @RequestParam(name = "name") String name) {
        model.addAttribute("customers", service.getCustomerByName(name));
        return "listOfCustomers";
    }

    @RequestMapping(value = "/remove/{id}")
    public String removeCustomerById(@PathVariable int id, Model model) {
        service.removeById(id);
        return getAllCustomers(model);
    }

    @RequestMapping(value = "/searchBy", method = RequestMethod.GET)
    public String searchCustomers(Model model,
                                  @RequestParam String condition) {
        try {
            int id = Integer.parseInt(condition);
            Customer customer = service.getCustomerById(id);
            if (customer == null)
                throw new CustomerNotFoundException();
            model.addAttribute("customers", customer);
            return "listOfCustomers";
        } catch (Exception e) {
            List<Customer> customerList = service.getCustomerByName(condition);
            model.addAttribute("customers", customerList);
            if (customerList == null)
                throw new CustomerNotFoundException();
            return "listOfCustomers";
        }
    }
}