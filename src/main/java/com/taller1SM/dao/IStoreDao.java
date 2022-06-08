package com.taller1SM.dao;

import java.util.List;
import java.util.Optional;

import com.taller1SM.model.sales.Store;

public interface IStoreDao {

	

	Optional<Store> findById(Integer businessentityid);

	List<Store> findAll();

	void save(Store entity);

	void update(Store entity);

	void delete(Store entity);

}
