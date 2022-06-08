package com.taller1SM.dao;

import java.util.List;
import java.util.Optional;

import com.taller1SM.model.sales.Customer;

public interface ICostumerDao  {

	void save(Customer entity);

	void update(Customer entity);

	void delete(Customer entity);

	Optional<Customer> findById(Integer customerid);

	List<Customer> findAll();

}
