package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;


import com.taller1SM.Taller1SmApplication;
import com.taller1SM.businessdelegate.BusinessDelegate;
import com.taller1SM.model.prod.Location;
import com.taller1SM.model.prod.Product;
import com.taller1SM.model.prod.Productcosthistory;
import com.taller1SM.model.prod.Productinventory;
import com.taller1SM.model.sales.Customer;
import com.taller1SM.model.sales.Store;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = Taller1SmApplication.class)
public class DelegateBussinesTest {

	@Mock
	private static RestTemplate template;
	@InjectMocks
	private static BusinessDelegate businessDelegate;

	private final static String URL = "http://localhost:8080/api";
	private final static String PRO_URL = URL + "/templatesProduct/";
	private final static String LOC_URL = URL + "/templatesLocation/";
	private final static String PCH_URL = URL + "/templatesProductCostHistoric/";
	private final static String PI_URL = URL + "/templatesProductInventory/";
	private final static String COST_URL = URL + "/templatesCostumer/";
	private final static String STOR_URL = URL + "/templatesStore/";

	@BeforeAll
	public static void setUp() {
		businessDelegate = new BusinessDelegate();
		businessDelegate.setRestTemplate(template);
	}

	/// ________SAVE________
	@Test
	public void saveCustomer() {

		LocalDate date = LocalDate.of(2022, 6, 20);
		Customer customer = new Customer();
		customer.setRowguid(2);
		customer.setCustomerid(1);
		customer.setModifieddate(date);
		when(template.postForObject(COST_URL, customer, Customer.class)).thenReturn(customer);
		Customer test = businessDelegate.saveCustomer(customer);
		assertEquals(test.getRowguid(), 2);
		verify(template).postForObject(COST_URL, customer, Customer.class);
	}

	@Test
	public void saveLocation() {

		Location loc = new Location();
		loc.setLocationid(2);
		loc.setName("Cali007");
		loc.setAvailability(new BigDecimal(10));
		loc.setCostrate(new BigDecimal(1));

		when(template.postForObject(LOC_URL, loc, Location.class)).thenReturn(loc);
		Location test = businessDelegate.saveLocation(loc);
		assertEquals(test.getAvailability(), loc.getAvailability());
		verify(template).postForObject(LOC_URL, loc, Location.class);

	};

	@Test
	public void savesPHC() {

		Productcosthistory prcH = new Productcosthistory();
		Product pr = new Product();
		pr.setProductid(1);
		prcH.setProduct(pr);
		prcH.setId(1);
		prcH.setStandardcost(new BigDecimal(10));
		LocalDate date2 = LocalDate.of(2022, 4, 26);
		prcH.setEnddate(date2);

		when(template.postForObject(PCH_URL, prcH, Productcosthistory.class)).thenReturn(prcH);
		Productcosthistory test = businessDelegate.savesPHC(prcH);
		assertEquals(test.getId(), prcH.getId());
		verify(template).postForObject(PCH_URL, prcH, Productcosthistory.class);
	};

	@Test
	public void savePIR() {

		Productinventory pir = new Productinventory();
		pir.setId(1);
		pir.setQuantity(100);
		when(template.postForObject(PI_URL, pir, Productinventory.class)).thenReturn(pir);
		Productinventory test = businessDelegate.savePIR(pir);
		assertEquals(test.getQuantity(), pir.getQuantity());
		verify(template).postForObject(PI_URL, pir, Productinventory.class);

	};

	@Test
	public void saveProduct() {

		Product pr = new Product();
		pr.setProductid(1);
		pr.setWeight((long) 3);
		pr.setSize((long) 10);
		pr.setProductnumber("0001");
		LocalDate date = LocalDate.of(2022, 3, 26);

		LocalDate date2 = LocalDate.of(2022, 4, 26);

		pr.setSellenddate(date2);
		pr.setSellstartdate(date);
		when(template.postForObject(PRO_URL, pr, Product.class)).thenReturn(pr);
		Product test = businessDelegate.saveProduct(pr);
		assertEquals(test.getProductnumber(), pr.getProductnumber());
		verify(template).postForObject(PRO_URL, pr, Product.class);
	};

	@Test
	public void saveStore() {
		Store store = new Store();
		store.setBusinessentityid(1);
		LocalDate date = LocalDate.of(2022, 6, 26);
		store.setModifieddate(date);
		store.setName("Miami-store PC");
		store.setDemographics("Miami");

	
		when(template.postForObject(STOR_URL, store, Store.class)).thenReturn(store);
		 Store test = businessDelegate.saveStore(store);
		assertEquals(test.getName(), store.getName());
		verify(template).postForObject(STOR_URL, store, Store.class);

	};

	/// ________EDIT________
	@Test
	public void editCustomer() {
		LocalDate date = LocalDate.of(2022, 6, 20);
		Customer customer= new Customer();
		customer.setRowguid(2);
		customer.setCustomerid(1);
		customer.setModifieddate(date);
		
	    businessDelegate.editCustomer(customer);

	    verify(template).put(COST_URL + customer.getCustomerid(), customer);

	}

	@Test
	public void editLocation() {
	
		Location loc = new Location();
		loc.setLocationid(2);
		loc.setName("Cali007");
		loc.setAvailability(new BigDecimal(10));
		loc.setCostrate(new BigDecimal(1));
		
	    businessDelegate.editLocation(loc);

	    verify(template).put(LOC_URL ,loc);
	}

	@Test
	public void editsPHC() {
		
		Productcosthistory prcH = new Productcosthistory();
		Product pr = new Product();
		pr.setProductid(1);
		prcH.setProduct(pr);
		prcH.setId(1);
		prcH.setStandardcost(new BigDecimal(10));
		LocalDate date2 = LocalDate.of(2022, 4, 26);
		prcH.setEnddate(date2);
	
	    businessDelegate.editsPHC(prcH);

	    verify(template).put(PCH_URL + prcH.getId(), prcH);
	}

	@Test
	public void editPIR() {
		Productinventory pir = new Productinventory();
		pir.setId(1);
		pir.setQuantity(100);	
	    businessDelegate.editPIR(pir);

	    verify(template).put(PI_URL + pir.getId(), pir);
	}

	@Test
	public void editProduct() {
		

		Product pr = new Product();
		pr.setProductid(1);
		pr.setWeight((long) 3);
		pr.setSize((long) 10);
		pr.setProductnumber("0001");
		LocalDate date = LocalDate.of(2022, 3, 26);

		LocalDate date2 = LocalDate.of(2022, 4, 26);

		pr.setSellenddate(date2);
		pr.setSellstartdate(date);
	    businessDelegate.editProduct(pr);

	    verify(template).put(PRO_URL + pr.getProductid(), pr);
	}

	@Test
	public void editStore() {
		Store store = new Store();
		store.setBusinessentityid(1);
		
		store.setName("Miami-store PC");
		store.setDemographics("Miami");

	    businessDelegate.editStore(store);

	    verify(template).put(STOR_URL , store);
	}
	/// ________DELETE________

	@Test
	public void deleteStore() {
		
		
		Store store = new Store();
		store.setBusinessentityid(1);
	
		businessDelegate.saveStore(store);
		
		businessDelegate.deleteStore(store.getBusinessentityid());
		
		verify(template).delete(STOR_URL+store.getBusinessentityid(),Integer.class);
	}

	@Test
	public void deleteCustomer() {
		Customer customer = new Customer();
	customer.setCustomerid(1);
	
	
	
		businessDelegate.saveCustomer(customer);
		
		businessDelegate.deleteCustomer(customer.getCustomerid());
		verify(template).delete(COST_URL+customer.getCustomerid(),Integer.class);
	}
	@Test
	public void deleteLocation() {}
	@Test
	public void deletesPHC() {}
	@Test
	public void deletePIR() {}
	@Test
	public void deleteProduct() {}

	/// ________FIND________
	@Test
	public void findCustomer() {
		Customer customer = new Customer();
		customer.setRowguid(2);
		customer.setCustomerid(1);
		when(template.getForObject(COST_URL+customer.getCustomerid(), Customer.class)).thenReturn(customer);
		assertEquals(businessDelegate.findCustomer(1).getRowguid(), customer.getRowguid());
	}

	@Test
	public void findLocation() {
		Location loc = new Location();
		loc.setLocationid(2);
		loc.setName("Cali007");
		loc.setAvailability(new BigDecimal(10));
		loc.setCostrate(new BigDecimal(1));
		when(template.getForObject(LOC_URL+loc.getLocationid(), Location.class)).thenReturn(loc);
		assertEquals(businessDelegate.findLocation(2).getLocationid(), loc.getLocationid());
	}

	@Test
	public void findsPHC() {
		
		Productcosthistory prcH = new Productcosthistory();
		Product pr = new Product();
		pr.setProductid(1);
		prcH.setProduct(pr);
		prcH.setId(1);
		prcH.setStandardcost(new BigDecimal(10));
		LocalDate date2 = LocalDate.of(2022, 4, 26);
		prcH.setEnddate(date2);
		when(template.getForObject(PCH_URL+prcH.getId(), Productcosthistory.class)).thenReturn(prcH);
		assertEquals(businessDelegate.findsPHC(1).getId(), prcH.getId());
	}

	@Test
	public void findPIR() {
		
		Productinventory pir = new Productinventory();
		pir.setId(1);
		pir.setQuantity(100);
		
		when(template.getForObject(PI_URL+pir.getId()
		, Productinventory.class)).thenReturn(pir);
		assertEquals(businessDelegate.findPIR(1).getQuantity(),pir.getQuantity());
	}

	@Test
	public void findProduct() {
		

		Product pr = new Product();
		pr.setProductid(1);
		pr.setWeight((long) 3);
		pr.setSize((long) 10);
		pr.setProductnumber("0001");
		LocalDate date = LocalDate.of(2022, 3, 26);

		LocalDate date2 = LocalDate.of(2022, 4, 26);

		pr.setSellenddate(date2);
		pr.setSellstartdate(date);
		
		when(template.getForObject(PRO_URL+pr.getProductid()
		, Product.class)).thenReturn(pr);
		assertEquals(businessDelegate.findProduct(1).getName(),pr.getName());
	}

	@Test
	public void findStore() {
		
		Store store = new Store();
		store.setBusinessentityid(1);
		LocalDate date = LocalDate.of(2022, 6, 26);
		store.setModifieddate(date);
		store.setName("Miami-store PC");
		store.setDemographics("Miami");
		
		when(template.getForObject(STOR_URL+store.getBusinessentityid()
		, Store.class)).thenReturn(store);
		assertEquals(businessDelegate.findStore(1).getName(),store.getName());
	};
	
	//__________________FIND BY ALL____________________-
	@Test
	public void findAllCustomer() {
		
		Customer[] list = new Customer[5];

	        for (int i = 0; i < list.length; i++) {
	           Customer ls = new Customer();
	            list[i] = ls;
	        }

	        when(template.getForObject(COST_URL,Customer[].class))
	                .thenReturn(list);

	        assertEquals(businessDelegate.findAllCustomer().size(), 5, "Unexpected size");
	}
	@Test
	public void findAllLocation() {

		Location[] list = new Location [5];

	        for (int i = 0; i < list.length; i++) {
	        	Location ls = new Location();
	            list[i] = ls;
	        }

	        when(template.getForObject(LOC_URL,Location[].class))
	                .thenReturn(list);

	        assertEquals(businessDelegate.findAllLocation().size(), 5, "Unexpected size");
	}
	@Test
	public void findAllsPHC() {

		Productcosthistory[] list = new Productcosthistory[5];

	        for (int i = 0; i < list.length; i++) {
	        	Productcosthistory ls = new Productcosthistory();
	            list[i] = ls;
	        }

	        when(template.getForObject(PCH_URL,Productcosthistory[].class))
	                .thenReturn(list);

	        assertEquals(businessDelegate.findAllsPHC().size(), 5, "Unexpected size");
	}
	@Test
	public void findAllPIR(){

		Productinventory[] list = new 	Productinventory[5];

	        for (int i = 0; i < list.length; i++) {
	        	Productinventory ls = new 	Productinventory();
	            list[i] = ls;
	        }

	        when(template.getForObject(PI_URL,	Productinventory[].class))
	                .thenReturn(list);

	        assertEquals(businessDelegate.findAllPIR().size(), 5, "Unexpected size");
	}
	@Test
	public void findAllProduct() {

		Product[] list = new Product[3];

	        for (int i = 0; i < list.length; i++) {
	           Product ls = new Product();
	            list[i] = ls;
	        }

	        when(template.getForObject(PRO_URL,Product[].class))
	                .thenReturn(list);

	        assertEquals(businessDelegate.findAllProduct().size(), 3, "Unexpected size");
	}
	@Test
	public void findAllStore() {

		Store[] list = new Store[5];

	        for (int i = 0; i < list.length; i++) {
	           Store ls = new Store();
	            list[i] = ls;
	        }

	        when(template.getForObject(STOR_URL,Store[].class))
	                .thenReturn(list);

	        assertEquals(businessDelegate.findAllStore().size(), 5, "Unexpected size");
	}
	

}
