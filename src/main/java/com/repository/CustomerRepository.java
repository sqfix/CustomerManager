package com.repository;

import com.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    List<Customer> findAll();

    Customer findById(int id);

    List<Customer> findByName(String name);

    void removeById(int id);
}
