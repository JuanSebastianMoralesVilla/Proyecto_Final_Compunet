package com.taller1SM.service;

import java.util.Optional;

import com.taller1SM.model.prod.Location;
import com.taller1SM.model.sales.Store;

public interface StoreService {

	void saveStore(Store store);

	Store editStore(Integer id, Store store);

	Optional<Store> findById(Integer id);

	void delete(Store store);

	Object findAll();

}
