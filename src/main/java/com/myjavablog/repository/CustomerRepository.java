package com.myjavablog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myjavablog.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
