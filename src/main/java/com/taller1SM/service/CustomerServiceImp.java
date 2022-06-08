package com.taller1SM.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.taller1SM.dao.TCustomerDao;
import com.taller1SM.dao.TStoreDao;
import com.taller1SM.model.sales.Customer;
@Service
public class CustomerServiceImp implements CustomerServiceI {

	private TCustomerDao  customerDao;
	private TStoreDao storeDao;

	public CustomerServiceImp(TCustomerDao customerDao,TStoreDao storeDao) {
		
		this.customerDao = customerDao;
		this.storeDao=storeDao;
	}
	
	
	@Override
	@Transactional
	public Customer saveCustomer(Customer costumer,Integer storeid) {
		if (costumer == null ) {
			throw new RuntimeException();
		

		}else {
			costumer.setStore(storeDao.findById(storeid).get());
		customerDao.save(costumer);
return costumer;
		}

	}
	
	
	@Override
	@Transactional
	public Customer editCustomer (Integer  id,Customer costumer,Integer storeid) {
		if (costumer== null || id== null) {
			throw new NullPointerException("customer no exist");
		} 
		costumer.setStore(storeDao.findById(storeid).get());
		
		
		Customer CustomerModified = customerDao.findById(id).get();
		CustomerModified.setStore(storeDao.findById(id).get());
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
