package com.taller1SM.restcontroller;

import com.taller1SM.model.prod.Product;

public interface ProductRestController {
	public void save(Product p);
	public void edit(Product p, Integer id);
	public void delete(Integer id);
	public Product findById(Integer id);
	public Iterable<Product> findAll();
}
