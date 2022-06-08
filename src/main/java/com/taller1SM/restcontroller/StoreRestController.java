package com.taller1SM.restcontroller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.taller1SM.model.sales.Store;

public interface StoreRestController {
	public void save(Store s);
	//public void edit(Store s, Integer id);
	public Store edit( Store s );
	public void deleteStore(Integer id);
	public Store findById(Integer id);
	public Iterable<Store> findAll();
}
