package com.taller1SM.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;

import com.taller1SM.model.prod.Product;

public interface QueryRestController {
	
	public Iterable<Product> findByProductSumInventory_orderByLocation();
	public Iterable<Product> findByProductCostHistoric();

}
