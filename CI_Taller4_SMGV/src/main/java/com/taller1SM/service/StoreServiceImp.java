package com.taller1SM.service;

import java.math.BigDecimal;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.taller1SM.dao.TStoreDao;
import com.taller1SM.model.prod.Location;
import com.taller1SM.model.prod.Product;
import com.taller1SM.model.sales.Store;

@Service
public class StoreServiceImp  implements StoreService{
	
	private TStoreDao  storeDao;

	public StoreServiceImp(TStoreDao storeDao) {
		
		this.storeDao = storeDao;
	}
	
	
	@Override
	@Transactional
	public void saveStore(Store store) {
		if (store == null ) {
			throw new RuntimeException();
		

		}else {
		storeDao.save(store);

		}

	}
	
	
	@Override
	@Transactional
	public Store editStore (Integer  id,Store store) {
		if (store== null || id== null) {
			throw new NullPointerException("store no exist");
		} 
		
		
		Store storeModified = storeDao.findById(id).get();
		storeModified.setName(store.getName());
		storeModified.setDemographics(store.getDemographics());
		storeModified.setModifieddate(store.getModifieddate());
		storeModified.setRowguid(store.getRowguid());
		storeDao.save(storeModified);
        return storeModified;
		
	}
	
	
	@Override
	public void delete(Store store) {
		storeDao.delete(store);
		
	}
	
	@Override
	public Iterable<Store> findAll() {
		return storeDao.findAll();
	}
	
	@Override
	public Optional<Store> findById(Integer id) {
		
		return storeDao.findById(id);
	}
	
	

}
