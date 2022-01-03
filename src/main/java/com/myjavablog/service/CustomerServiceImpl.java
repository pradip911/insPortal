package com.myjavablog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myjavablog.model.Customer;
import com.myjavablog.repository.CustomerRepository;
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{


    @Autowired
    CustomerRepository custRepo;

	@Override
	public List<Customer> getAllCustomer() {
		return (List<Customer>)custRepo.findAll();
	}

	@Override
	public void save(Customer customer) {
		custRepo.save(customer);
		
	}

}
