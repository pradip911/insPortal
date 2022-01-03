package com.myjavablog.service;

import java.util.List;

import com.myjavablog.model.Customer;

public interface CustomerService {
public List<Customer> getAllCustomer();
public void save(Customer customer);


}
