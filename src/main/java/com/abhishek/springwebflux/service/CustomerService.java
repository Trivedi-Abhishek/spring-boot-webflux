package com.abhishek.springwebflux.service;


import com.abhishek.springwebflux.dao.CustomerDao;
import com.abhishek.springwebflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    public CustomerDao customerDao;

    public List<Customer> getAllCustomers(){
        long start = System.currentTimeMillis();
        List<Customer> customers = customerDao.getAllCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time : " + (end - start));
        return customers;
    }
    public Flux<Customer> getAllCustomersStream(){
        long start = System.currentTimeMillis();
        Flux<Customer> customers = customerDao.getAllCustomersStream();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time : " + (end - start));
        return customers;
    }
}
