package com.taller1SM;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.taller1SM.model.prod.Location;
import com.taller1SM.model.prod.Product;
import com.taller1SM.model.prod.Productcategory;
import com.taller1SM.model.prod.Productcosthistory;
import com.taller1SM.model.prod.Productinventory;
import com.taller1SM.model.prod.Productsubcategory;
import com.taller1SM.model.sales.Customer;
import com.taller1SM.model.sales.Store;
import com.taller1SM.model.user.UserApp;
import com.taller1SM.model.user.UserType;
import com.taller1SM.repositories.LocationRepository;
import com.taller1SM.repositories.ProductRepository;
import com.taller1SM.repositories.ProductSubcategoryRepository;
import com.taller1SM.repositories.ProductcategoryRepository;
import com.taller1SM.repositories.ProductcosthistoryRepository;
import com.taller1SM.repositories.ProductinventoryRepository;
import com.taller1SM.repositories.UserRepository;
import com.taller1SM.service.CustomerServiceI;
import com.taller1SM.service.LocationService;
import com.taller1SM.service.ProductService;
import com.taller1SM.service.ProductcosthistoryService;
import com.taller1SM.service.ProductinventoryService;
import com.taller1SM.service.StoreService;



@ComponentScan(basePackages={"com.taller1SM"})
@SpringBootApplication

public class Taller1SmApplication {

	
	
	@Bean
	public  Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
		
		
	
	public static void main(String[] args) {
		SpringApplication.run(Taller1SmApplication.class, args);
	}
	
	
	@Bean
	public RestTemplate getTemplate() {
		RestTemplate template = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
		messageConverters.add(converter);
		template.setMessageConverters(messageConverters);
		return template;
	}
	
	

	@Bean
	public CommandLineRunner runner( 
			ProductSubcategoryRepository subcategoryRepo, ProductcategoryRepository categoryRepo,
			ProductinventoryRepository inventoryRepo, UserRepository userRepository,
			StoreService storeService, CustomerServiceI customerService, 
			ProductService productService, LocationService locationService,
			ProductcosthistoryService pchService, ProductinventoryService inventoryService) {
		
		return (args) -> {
			
			
		//----------usuarios----------------
			//  administrador gestiona productos y locations
			// user: admin contraseña : 1234
			// user: operador contraseña 1234
			
			
			UserApp userAdmin = new UserApp();
			userAdmin.setId(1193029087);
			userAdmin.setName("Administrador 1");
			userAdmin.setUsername("admin");
			userAdmin.setPassword("{noop}1234");
			userAdmin.setType(UserType.admin);
			userRepository.save(userAdmin);
			
			
			System.out.println("Cargamos los datos de admin");
			
//			User userop = new User();
			userAdmin.setId(123456789);
			userAdmin.setName("Operador 1");
			userAdmin.setUsername("operador");
			userAdmin.setPassword("{noop}1234");
			userAdmin.setType(UserType.operador);
			userRepository.save(userAdmin);
		
			System.out.println("Cargamos los datos de operador");
	
			Productcategory category = new Productcategory();
			category.setName("PRODUCTOS TECNOLOGIA");
			category.setProductsubcategories(new ArrayList<Productsubcategory>());
			
			Productsubcategory subcategory = new Productsubcategory();
			subcategory.setName("COMPUTADOR");
			subcategoryRepo.save(subcategory);
			category.addProductsubcategory(subcategory);
			
			subcategory = new Productsubcategory();
			subcategory.setName("CONSOLAS");
			//subcategoryRepo.save(subcategory);
			category.addProductsubcategory(subcategory);
			
			subcategory = new Productsubcategory();
			subcategory.setName("TELEFONOS");
			//subcategoryRepo.save(subcategory);
			category.addProductsubcategory(subcategory);
			categoryRepo.save(category);
			
			
			
			Product p = new Product();
			p.setProductnumber("03");
			p.setName("MACBOOK PRO 14");
			p.setSellstartdate(LocalDate.of(2022, 5, 20));
			p.setSellenddate(LocalDate.of(2022, 5, 28));
			p.setWeight(20);		
			p.setSize(3);
			p.setProductsubcategory(subcategoryRepo.findById(1).get());
			p.setProductcosthistories(new ArrayList<Productcosthistory>());
			productService.saveProduct(p,null, 1);
			
			p = new Product();
			p.setProductnumber("08");
			p.setName("MSI X");
			p.setSellstartdate(LocalDate.of(2022, 3, 28));
			p.setSellenddate(LocalDate.of(2022, 4, 2));
			p.setWeight(200);		
			p.setSize(5);
			productService.saveProduct(p,null, 1);
			
		
			p = new Product();
			p.setProductnumber("05");
			p.setName("LENOVO Y 540");
			p.setSellstartdate(LocalDate.of(2022, 2, 7));
			p.setSellenddate(LocalDate.of(2022, 2, 8));
			p.setWeight(200);		
			p.setSize(10);
			productService.saveProduct(p,null, 1);
			
		
			Location locationN = new Location();
			locationN.setName("CALI-7001");
			locationN.setAvailability(new BigDecimal(5));
			locationN.setCostrate(new BigDecimal(0));
			locationService.saveLocation(locationN);
			
			locationN = new Location();
			locationN.setName("BOG-5001");
			locationN.setAvailability(new BigDecimal(8));
			locationN.setCostrate(new BigDecimal(1));
			locationService.saveLocation(locationN);
			
			
		
		

			Productcosthistory pHistoric = new Productcosthistory();
			pHistoric.setModifieddate(LocalDate.of(2022, 5, 20));
			pHistoric.setEnddate(LocalDate.of(2022, 5, 28));
			pHistoric.setProduct(productService.findById(1).get());
			pHistoric.setStandardcost(new BigDecimal(80000));
			pchService.savePHC(pHistoric, 1);
			
			pHistoric = new Productcosthistory();
			pHistoric.setEnddate(LocalDate.of(2022, 3, 28));
			pHistoric.setModifieddate(LocalDate.of(2021, 4, 2));
			pHistoric.setProduct(productService.findById(1).get());
			pHistoric.setStandardcost(new BigDecimal(6000));
			pchService.savePHC(pHistoric, 1);
			
			pHistoric = new Productcosthistory();
			pHistoric.setEnddate(LocalDate.of(2022, 2, 15));
			pHistoric.setModifieddate(LocalDate.of(2021, 8, 2));
			pHistoric.setProduct(productService.findById(2).get());
			pHistoric.setStandardcost(new BigDecimal(10000));
			pchService.savePHC(pHistoric, 2);
			
			
			
//			Optional<Product> modifiedP = productService.findById(1);
//			Product modifiedProduct = modifiedP.get();
//			
//			if(modifiedProduct != null) {
//				modifiedProduct.addProductcosthistory(pHistoric);
//				modifiedProduct.addProductcosthistory(pHistoric2);
//				productService.editProduct(modifiedProduct.getProductid(), modifiedProduct, null, modifiedProduct.getProductsubcategory().getProductsubcategoryid());
//			}
			
			Productinventory inventary = new Productinventory();
			inventary.setLocation(locationService.findById(1).get());
			inventary.setProduct(productService.findById(1).get());
			inventary.setQuantity(12);
			inventoryService.savePIR(inventary, 1,1);
			
			inventary = new Productinventory();
			inventary.setLocation(locationService.findById(2).get());
			inventary.setProduct(productService.findById(2).get());
			inventary.setQuantity(43);
			inventoryService.savePIR(inventary, 2,1);
		
			
			Store store = new Store();
				
			store.setDemographics("Caucasian");
			store.setName("LENOVO");
			store.setRowguid(23);
			store.setModifieddate(LocalDate.of(2022, 12, 12));
			storeService.saveStore(store);
			
		
			
			store.setDemographics("Asiatica");
			store.setName("APPLE");
			store.setRowguid(2);
			store.setModifieddate(LocalDate.of(2022, 10, 12));
			storeService.saveStore(store);
			
			Customer customer = new Customer();
			customer.setModifieddate(LocalDate.of(2022, 10, 12));
			customer.setRowguid(2);
			customer.setStore(storeService.findById(1).get());
			customerService.saveCustomer(customer,1);
			
	
		
		

		};
			
			
	}
}
