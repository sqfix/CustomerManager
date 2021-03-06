package com;

import com.model.Customer;
import com.service.CustomUserDetails;
import com.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, CustomerService service) throws Exception {
        service.saveCustomer(new Customer("Andrew", "+1-202-555-0189", "San Jose"));
        service.saveCustomer(new Customer("Kayla", "+1-202-555-0179", "Dallas"));
        builder.userDetailsService(customer -> new CustomUserDetails(service.getCustomerByName(customer)));
    }
}
