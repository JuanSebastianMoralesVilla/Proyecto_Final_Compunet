package com.taller1SM.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.taller1SM.model.sales.Store;
import com.taller1SM.service.StoreService;

@RestController
public class StoreRestControllerImp implements StoreRestController {

	@Autowired
	private StoreService service;
	
	@Override
	@PostMapping("/api/templatesStore/")
	public void save(@RequestBody Store s) {
		service.saveStore(s);

	}

	@Override
	@RequestMapping(
			value = "/api/templatesStore/", 
			  produces = "application/json", 
			  method=RequestMethod.PUT)
	public Store edit(@RequestBody Store s ) {
		return service.editStore(s);

	}
	
	


	@Override
	@DeleteMapping("/api/templatesStore/{id}")
	public void deleteStore(@PathVariable("id") Integer id) {
		Store store = service.findById(id).get();
		service.delete(store);
	}

	@Override
	@GetMapping("/api/templatesStore/{id}")
	public Store findById(@PathVariable("id") Integer id) {
		return service.findById(id).get();
		
	}

	@Override
	@GetMapping("/api/templatesStore/")
	public Iterable<Store> findAll() {
		return service.findAll();
		
	}

}
