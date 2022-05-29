package com.taller1SM.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.taller1SM.model.sales.Customer;

public class TCostumerDao implements ICostumerDao {

	
	

	@PersistenceContext
	private EntityManager entityManagerStore;

	@Override
	public void save(Customer entity) {
		entityManagerStore.merge(entity);
		System.out.println("g customerid uardado en el dao bd");
	}

	@Override
	public void update(Customer entity) {
		entityManagerStore.merge(entity);
		System.out.println(" customerid u modificado con dao bd");
	}

	@Override
	public void delete(Customer entity) {
		entityManagerStore.remove(entity);
		System.out.println(" customerid u Eliminado con dao bd");
	}

	@Override
	public Optional<Customer> findById(Integer customerid) {
		return Optional.ofNullable(entityManagerStore.find(Customer.class, customerid));
	}

	@Override
	public List<Customer> findAll() {
		String jpql = "customerid  a from Customer a";
		return entityManagerStore.createQuery(jpql).getResultList();
	}

}
