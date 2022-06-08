package com.taller1SM.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller1SM.model.prod.Product;
import com.taller1SM.model.prod.Productsubcategory;
import com.taller1SM.service.ProductService;

@RestController
public class QueryRestControllerImp implements QueryRestController{
	
	@Autowired
	private ProductService service;
	
	@Override
	@GetMapping("/api/templatesQuery/one")
	public Iterable<Product> findByProductSumInventory_orderByLocation() {
		Productsubcategory x = service.findById(1).get().getProductsubcategory();
		return service.query1(x);
		
	}
	
	@Override
	@GetMapping("/api/templatesQuery/two")
	public Iterable<Product> findByProductCostHistoric() {
		return service.query2();
		
	}

}
