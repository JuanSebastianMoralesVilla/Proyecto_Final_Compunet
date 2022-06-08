package com.taller1SM.businessdelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.taller1SM.model.prod.Location;
import com.taller1SM.model.prod.Product;
import com.taller1SM.model.prod.Productcosthistory;
import com.taller1SM.model.prod.Productinventory;
import com.taller1SM.model.sales.Customer;
import com.taller1SM.model.sales.Store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Component
public class BusinessDelegate implements BusinessDelgateI {
	
	private final static String URL = "http://localhost:8080/api";
    private final static String PRO_URL = URL + "/templatesProduct/";
    private final static String LOC_URL = URL + "/templatesLocation/";
    private final static String PCH_URL = URL + "/templatesProductCostHistoric/";
    private final static String PI_URL = URL + "/templatesProductInventory/";
    private final static String COST_URL = URL + "/templatesCostumer/";
    private final static String STOR_URL = URL + "/templatesStore/";
    private final static String QUERY_URL = URL + "/templatesQuery/";
    
    private RestTemplate restTemplate;

    @Autowired
    public BusinessDelegate() {
    	this.restTemplate = new RestTemplate();
    } 
    
    public void setRestTemplate(RestTemplate rt){
        this.restTemplate = rt;
    }

////////______________________SAVE ______________________________
    
	@Override
	public Customer saveCustomer(Customer customer) {
		return restTemplate.postForObject(COST_URL, customer, Customer.class);
		
	}

	@Override
	public Location saveLocation(Location location) {
		return restTemplate.postForObject(LOC_URL, location, Location.class);
		
	}

	@Override
	public Productcosthistory savesPHC(Productcosthistory productcosthistory) {
		return restTemplate.postForObject(PCH_URL, productcosthistory, Productcosthistory.class);
		
	}

	@Override
	public Productinventory savePIR(Productinventory productinventory) {
		return restTemplate.postForObject(PI_URL, productinventory, Productinventory.class);
		
	}

	@Override
	public Product saveProduct(Product product) {
		return restTemplate.postForObject(PRO_URL, product, Product.class);
		
	}

	@Override
	public Store saveStore(Store store) {
		return restTemplate.postForObject(STOR_URL, store, Store.class);
		
	}

	
	
	
	
//////// ______________________EDIT ENTITIES______________________________
	@Override
	public void editCustomer(Customer customer) {
		restTemplate.put(COST_URL + customer.getCustomerid(), customer);
		
	}
	
	@Override
	public void editStore(Store store) {
		restTemplate.put(STOR_URL , store);}

	@Override
	public void editLocation(Location location) {
		restTemplate.put(LOC_URL ,location);
		
	}

	@Override
	public void editsPHC(Productcosthistory productcosthistory) {
		restTemplate.put(PCH_URL + productcosthistory.getId(), productcosthistory);
		
	}

	@Override
	public void editPIR(Productinventory productinventory) {
		restTemplate.put(PI_URL + productinventory.getId(), productinventory);
		
	}

	@Override
	public void editProduct(Product product) {
		restTemplate.put(PRO_URL + product.getProductid(), product);
		
	}


		
	
////////______________________DELETE______________________________
    
	@Override
	public void deleteCustomer(Integer customer) {
		restTemplate.delete(COST_URL + customer, Integer.class);
		
	}

	@Override
	public void deleteLocation(Integer location) {
		restTemplate.delete(LOC_URL + location, Integer.class);
		
	}

	@Override
	public void deletesPHC(Integer productcosthistory) {
		restTemplate.delete(PCH_URL + productcosthistory, Integer.class);
		
	}

	@Override
	public void deletePIR(Integer productinventory) {
		restTemplate.delete(PI_URL + productinventory, Integer.class);
		
	}

	@Override
	public void deleteProduct(Integer product) {
		restTemplate.delete(PRO_URL + product, Integer.class);
		
	}

	@Override
	public void deleteStore(Integer store) {
		restTemplate.delete(STOR_URL + store, Integer.class);
		
	}

////////______________________FINDS______________________________
	@Override
	public Customer findCustomer(Integer customer) {
		return restTemplate.getForObject(COST_URL + customer, Customer.class);
		
	}

	@Override
	public Location findLocation(Integer location) {
		return restTemplate.getForObject(LOC_URL + location, Location.class);
		
	}

	@Override
	public Productcosthistory findsPHC(Integer productcosthistory) {
		return restTemplate.getForObject(PCH_URL + productcosthistory, Productcosthistory.class);
		
	}

	@Override
	public Productinventory findPIR(Integer productinventory) {
		return restTemplate.getForObject(PI_URL + productinventory, Productinventory.class);
		
	}

	@Override
	public Product findProduct(Integer product) {
		return restTemplate.getForObject(PRO_URL + product, Product.class);
		
	}

	@Override
	public Store findStore(Integer store) {
		return restTemplate.getForObject(STOR_URL + store, Store.class);
		
	}
////////______________________FIND ALL______________________________
	@Override
	public List<Customer> findAllCustomer() {
		return Arrays.asList(restTemplate.getForObject(COST_URL, Customer[].class));
		
	}

	@Override
	public List<Location> findAllLocation() {
		return Arrays.asList(restTemplate.getForObject(LOC_URL, Location[].class));
		
	}

	@Override
	public List<Productcosthistory> findAllsPHC() {
		return Arrays.asList(restTemplate.getForObject(PCH_URL, Productcosthistory[].class));
		
	}
	
	@Override
	public List<Product> findByProductSumInventory_orderByLocation() {
		return Arrays.asList(restTemplate.getForObject(QUERY_URL+"one",Product[].class));
		
	}

	@Override
	public List<Productinventory> findAllPIR() {
		return Arrays.asList(restTemplate.getForObject(PI_URL, Productinventory[].class));
		
	}

	@Override
	public List<Product> findAllProduct() {
		return Arrays.asList(restTemplate.getForObject(PRO_URL, Product[].class));
		
	}

	@Override
	public List<Store> findAllStore() {
		return Arrays.asList(restTemplate.getForObject(STOR_URL,Store[].class));
		
	}


	
	@Override
	public List<Product> findByProductCostHistoric() {
		return Arrays.asList(restTemplate.getForObject(QUERY_URL+"two",Product[].class));
		
	}
    
    
    
    
    
} //end of class
