package com.taller1SM.businessdelegate;

import java.util.Arrays;
import java.util.List;

import com.taller1SM.model.prod.Location;
import com.taller1SM.model.prod.Product;
import com.taller1SM.model.prod.Productcosthistory;
import com.taller1SM.model.prod.Productinventory;
import com.taller1SM.model.sales.Customer;
import com.taller1SM.model.sales.Store;

public interface BusinessDelgateI {
	
	public Customer saveCustomer(Customer customer);
	public Location saveLocation(Location location);
	public Productcosthistory savesPHC(Productcosthistory productcosthistory);
	public Productinventory savePIR(Productinventory productinventory);
	public Product saveProduct(Product product);
	public Store saveStore(Store store);
	
	public void editCustomer(Customer customer);
	public void editLocation(Location location);
	public void editsPHC(Productcosthistory productcosthistory);
	public void editPIR(Productinventory productinventory);
	public void editProduct(Product product);
	public void editStore(Store store);
	
	public void deleteCustomer(Integer customer);
	public void deleteLocation(Integer location);
	public void deletesPHC(Integer productcosthistory);
	public void deletePIR(Integer productinventory);
	public void deleteProduct(Integer product);
	public void deleteStore(Integer store);
	
	public Customer findCustomer(Integer customer);
	public Location findLocation(Integer location);
	public Productcosthistory findsPHC(Integer productcosthistory);
	public Productinventory findPIR(Integer productinventory);
	public Product findProduct(Integer product);
	public Store findStore(Integer store);
	
	public List<Customer> findAllCustomer();
	public List<Location> findAllLocation();
	public List<Productcosthistory> findAllsPHC();
	public List<Productinventory> findAllPIR();
	public List<Product> findAllProduct();
	public List<Store> findAllStore();
	
	public List<Product> findByProductSumInventory_orderByLocation();
	
	public List<Product> findByProductCostHistoric();
	
}
