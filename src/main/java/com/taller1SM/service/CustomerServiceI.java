package com.taller1SM.service;

import java.util.Optional;

import com.taller1SM.model.sales.Customer;

public interface CustomerServiceI {

	

	void delete(Customer customer);

	Iterable<Customer> findAll();

	Optional<Customer> findById(Integer id);

	Customer saveCustomer(Customer costumer, Integer storeid);

	Customer editCustomer(Integer id, Customer costumer, Integer storeid);

}
