package com.taller1SM.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taller1SM.model.prod.Productcosthistory;
import com.taller1SM.service.ProductcostHistoricImp;

@RestController
public class ProductCostHistoricRestControllerImp implements ProductCostHistoricRestController {

	@Autowired
	private ProductcostHistoricImp service;
	
	@Override
	@PostMapping("/api/templatesProductCostHistoric/")
	public void save(@RequestBody Productcosthistory p) {
		service.savePHC(p, p.getProduct().getProductid());
		
	}

	@Override
	@PutMapping("/api/templatesProductCostHistoric/{id}")
	public void edit(@RequestBody Productcosthistory p, @PathVariable("id") Integer id) {
		service.editPHC(id, p, p.getProduct().getProductid());
		
	}



	@Override
	@GetMapping("/api/templatesProductCostHistoric/{id}")
	public Productcosthistory findById(@PathVariable("id") Integer id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
		
	}

	@Override
	@GetMapping("/api/templatesProductCostHistoric/")
	public Iterable<Productcosthistory> findAll() {
		return service.findAll();
		
	}

}
