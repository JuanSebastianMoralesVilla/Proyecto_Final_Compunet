package com.taller1SM.restcontroller;

import com.taller1SM.model.prod.Productinventory;

public interface ProductInventoryRestController {
	public void save(Productinventory p);
	public void edit(Productinventory p, Integer id);

	public Productinventory findById(Integer id);
	public Iterable<Productinventory> findAll();
}
