package com.taller1SM.service;

import java.util.List;
import java.util.Optional;

import com.taller1SM.model.prod.Product;
import com.taller1SM.model.prod.Productsubcategory;

public interface ProductService {

	public Product saveProduct(Product product, Integer prCategoryId, Integer prScategoryId);

	
	public Iterable<Product> findAll();
	public Iterable<Productsubcategory> findAllSubcategory();

	public Optional<Product> findById(Integer id);

	public Product editProduct(Integer id, Product product, Integer prCategoryId, Integer prSubcategoryId);


	void delete(Product product);
	
	public  List<Product> query1(Productsubcategory pSubcategory);
	public  List<Product> query2();
}
