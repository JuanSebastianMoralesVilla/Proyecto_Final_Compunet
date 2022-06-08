package com.taller1SM.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taller1SM.model.prod.Product;
import com.taller1SM.service.ProductService;

@RestController
public class ProductRestControllerImp implements ProductRestController {

	@Autowired
	private ProductService service;
	
	@Override
	@PostMapping("/api/templatesProduct/")
	public void save(@RequestBody Product p) {
		service.saveProduct(p, null, 1);

	}

	@Override
	@PutMapping("/api/templatesProduct/{id}")
	public void edit(@RequestBody Product p, @PathVariable("id") Integer id) {
		service.editProduct(id, p, null, 1);

	}

	@Override
	@DeleteMapping("/api/templatesProduct/{id}")
	public void delete(@PathVariable("id") Integer id) {
		service.delete(service.findById(id).get());
	}

	@Override
	@GetMapping("/api/templatesProduct/{id}")
	public Product findById(@PathVariable("id") Integer id) {
		return service.findById(id).get();
		
	}

	@Override
	@GetMapping("/api/templatesProduct/")
	public Iterable<Product> findAll() {
		return service.findAll();
		
	}

}
