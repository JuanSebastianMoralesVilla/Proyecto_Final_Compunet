package com.taller1SM.restcontroller;

import com.taller1SM.model.sales.Customer;

public interface CustomerRestController {
	public void save(Customer c);
	public void edit(Customer c, Integer id);
	public void delete(Integer id);
	public Customer findById(Integer id);
	public Iterable<Customer> findAll();
}
