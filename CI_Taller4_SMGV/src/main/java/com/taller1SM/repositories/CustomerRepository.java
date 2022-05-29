package com.taller1SM.repositories;

import org.springframework.data.repository.CrudRepository;

import com.taller1SM.model.sales.Customer;

public interface CustomerRepository  extends CrudRepository<Customer, Integer>{

}
