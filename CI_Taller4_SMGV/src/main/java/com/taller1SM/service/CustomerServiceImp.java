package com.taller1SM.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.taller1SM.dao.TCustomerDao;
import com.taller1SM.model.sales.Customer;
import com.taller1SM.model.sales.Store;
@Service
public class CustomerServiceImp implements CustomerServiceI {

	private TCustomerDao  customerDao;

	public CustomerServiceImp(TCustomerDao customerDao) {
		
		this.customerDao = customerDao;
	}
	
	
	@Override
	@Transactional
	public void saveCustomer(Customer costumer) {
		if (costumer == null ) {
			throw new RuntimeException();
		

		}else {
		customerDao.save(costumer);

		}

	}
	
	
	@Override
	@Transactional
	public Customer editCustomer (Integer  id,Customer costumer) {
		if (costumer== null || id== null) {
			throw new NullPointerException("customer no exist");
		} 
		
		
		Customer CustomerModified = customerDao.findById(id).get();

		CustomerModified.setPersonid(costumer.getPersonid());
		CustomerModified.setModifieddate(costumer.getModifieddate());
		CustomerModified.setRowguid(costumer.getRowguid());
		customerDao.save(CustomerModified);
        return CustomerModified;
		
	}
	
	
	@Override
	@Transactional
	public void delete(Customer customer) {
		customerDao.delete(customer);
		
	}
	
	@Override
	public Iterable<Customer> findAll() {
		return customerDao.findAll();
	}
	
	@Override
	public Optional<Customer> findById(Integer id) {
		
		return customerDao.findById(id);
	}
	
}
