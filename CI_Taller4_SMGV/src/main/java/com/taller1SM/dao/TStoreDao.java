package com.taller1SM.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.taller1SM.model.prod.Product;
import com.taller1SM.model.sales.Store;

@Repository
@Scope("singleton")
public class TStoreDao implements IStoreDao{
	
	

	@PersistenceContext
	private EntityManager entityManagerStore;

	@Override
	public void save(Store entity) {
		entityManagerStore.merge(entity);
		System.out.println("g Store uardado en el dao bd");
	}

	@Override
	public void update(Store  entity) {
		entityManagerStore.merge(entity);
		System.out.println(" Store u modificado con dao bd");
	}

	@Override
	public void delete(Store  entity) {
		entityManagerStore.remove(entity);
		System.out.println(" Store u Eliminado con dao bd");
	}

	@Override
	public Optional<Store> findById(Integer businessentityid) {
		return Optional.ofNullable(entityManagerStore.find(Store.class, businessentityid));
	}

	@Override
	public List<Store> findAll() {
		String jpql = "Select a from Store a";
		return entityManagerStore.createQuery(jpql).getResultList();
	}

}
