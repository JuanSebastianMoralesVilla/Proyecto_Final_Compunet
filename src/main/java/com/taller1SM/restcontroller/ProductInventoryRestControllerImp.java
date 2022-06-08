package com.taller1SM.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taller1SM.model.prod.Productinventory;
import com.taller1SM.service.ProductinventoryService;

@RestController
public class ProductInventoryRestControllerImp implements ProductInventoryRestController {

	@Autowired
	private ProductinventoryService service;
	
	@Override
	@PostMapping("/api/templatesProductInventory/")
	public void save(@RequestBody Productinventory p) {
		service.savePIR(p, p.getProduct().getProductid(), p.getLocation().getLocationid());
		//service.savePIR(p, p.getProduct().getProductid());

	}

	@Override
	@PutMapping("/api/templatesProductInventory/{id}")
	public void edit(@RequestBody Productinventory p, @PathVariable("id") Integer id) {
		service.editPIR(id, p, p.getProduct().getProductid(), p.getLocation().getLocationid());
		//service.editPIR(id,p, p.getProduct().getProductid());

	}



	@Override
	@GetMapping("/api/templatesProductInventory/{id}")
	public Productinventory findById(@PathVariable("id") Integer id) {
		return service.findById(id).get();
		
		
	}

	@Override
	@GetMapping("/api/templatesProductInventory/")
	public Iterable<Productinventory> findAll() {
		return service.findAll();
		
	}

}
