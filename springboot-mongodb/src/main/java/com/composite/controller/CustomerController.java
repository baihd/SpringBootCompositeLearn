package com.composite.controller;

import com.composite.dao.CustomerRepository;
import com.composite.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @ResponseBody
    @RequestMapping(value = "save")
    public void save() {
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer("san", "zhang");
        customers.add(customer);
        repository.save(customers);
    }

    @ResponseBody
    @RequestMapping(value = "selectByName")
    public void selectByName() {
        Customer customer = repository.findByFirstName("Bob");
        List<Customer> customers = repository.findByLastName("Smith");
        System.out.println(customer);
        System.out.println(customers);
    }

    @ResponseBody
    @RequestMapping(value = "selectAll")
    public void selectAll() {
        List<Customer> customers = repository.findAll();
        System.out.println(customers.size());
    }

    @ResponseBody
    @RequestMapping(value = "deleteAll")
    public void deleteAll() {
        repository.deleteAll();
    }
}
