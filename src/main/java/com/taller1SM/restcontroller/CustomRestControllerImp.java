package com.taller1SM.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taller1SM.model.sales.Customer;
import com.taller1SM.service.CustomerServiceI;

@RestController
public class CustomRestControllerImp implements CustomerRestController {

	@Autowired
	private CustomerServiceI service;
	
	@Override
	@PostMapping("/api/templatesCostumer/")
	public void save(@RequestBody Customer c) {
		//antes solo c
		service.saveCustomer(c,c.getStore().getBusinessentityid());

	}

	@Override
	@PutMapping("/api/templatesCostumer/{id}")
	public void edit(@RequestBody Customer c, @PathVariable("id") Integer id) {
		service.editCustomer(id, c,c.getStore().getBusinessentityid());

	}

	@Override
	@DeleteMapping("/api/templatesCostumer/{id}")
	public void delete(@PathVariable("id") Integer id) {
		service.delete(service.findById(id).get());

	}

	@Override
	@GetMapping("/api/templatesCostumer/{id}")
	public Customer findById(@PathVariable("id") Integer id) {
		return service.findById(id).get();
		
	}

	@Override
	@GetMapping("/api/templatesCostumer/")
	public Iterable<Customer> findAll() {
		return service.findAll();
		
	}

}
