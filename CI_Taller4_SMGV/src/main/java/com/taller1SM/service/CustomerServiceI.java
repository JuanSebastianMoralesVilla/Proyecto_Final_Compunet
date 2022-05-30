package com.taller1SM.service;

import java.util.Optional;

import com.taller1SM.model.sales.Customer;

public interface CustomerServiceI {

	void saveCustomer(Customer costumer);

	Customer editCustomer(Integer id, Customer costumer);

	void delete(Customer customer);

	Iterable<Customer> findAll();

	Optional<Customer> findById(Integer id);

}
