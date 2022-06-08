package com.taller1SM.restcontroller;

import com.taller1SM.model.prod.Productcosthistory;

public interface ProductCostHistoricRestController {
	public void save(Productcosthistory p);
	public void edit(Productcosthistory p, Integer id);

	public Productcosthistory findById(Integer id);
	public Iterable<Productcosthistory> findAll();
}
