package com.taller1SM.controller.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taller1SM.controller.interfaces.CustomerController;
import com.taller1SM.model.sales.Customer;
import com.taller1SM.model.sales.Store;
import com.taller1SM.service.CustomerServiceI;
import com.taller1SM.service.StoreService;

@Controller
public class CustomerControllerImp implements CustomerController{

	
	private CustomerServiceI customerService;

	@Autowired

	public CustomerControllerImp(CustomerServiceI customerService) {
		
		this.customerService = customerService;
	}
	

	@GetMapping("/templatesCustomer/")
	public String indexCust(Model model) {
		model.addAttribute("customers", customerService.findAll());
		System.out.println("ENTRE A LA PAGINA de costumers");
		return "templatesCustomer/Index";
	}
	

	@GetMapping("/templatesCustomer/add-customer")
	public String listCostumers(Model model) {
		model.addAttribute("customer", new Customer());
		

		return "templatesCustomer/add-customer";
	}
	
	@PostMapping("/templatesCustomer/add-customer/")
	public String saveCustomer(@Validated @ModelAttribute Customer customer, BindingResult bindingResult, Model model,
			@RequestParam(value = "action", required = true) String action) {

		if (!action.equals("Cancel")) {
		if (bindingResult.hasErrors()) {
				return "templatesCustomer/add-customer";
			} 
			
		//	else if (store.getModifieddate().) {
			//	model.addAttribute("dateError", true);
			//	return "templatesProduct/add-product";
			//}
		
			customerService.saveCustomer(customer);
		}
		return "redirect:/templatesCustomer/";
	}
	
	@GetMapping("/templatesCustomer/del/{id}")
	public String deleteCustomer(@PathVariable("id") Integer id, Model model) {
		Customer cust = customerService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		customerService.delete(cust);
		model.addAttribute("customer", customerService.findAll());
		return "templatesCustomer/Index";
	}
	
	@GetMapping("/templatesCustomer/edit/{id}")
	public String showStore(@PathVariable("id") Integer id, Model model) {
	
		Optional<Customer> customer = customerService.findById(id);
		if (customer == null)
			throw new IllegalArgumentException("Invalid customer Id:" + id);
		model.addAttribute("customer", customer.get());

		return "templatesCustomer/update-customer";
	}
	
	@PostMapping("/templatesCustomer/edit/{id}")
	public String updateStore(
			@PathVariable("id") Integer id, 
			@RequestParam(value = "action", required = true) String action,
			@Validated @ModelAttribute Customer cust, 
			BindingResult bindingResult,
			Model model
			) {
		if (action.equals("Cancel"))
			return "redirect:/templatesCustomer/";
		if (bindingResult.hasErrors()) {
	cust.setCustomerid(id);
			return "templatesCustomer/update-customer";
		}
		if (!action.equals("Cancel")) {
			customerService.editCustomer(id, cust);
			model.addAttribute("customers", customerService.findAll());
		}
		return "redirect:/templatesCustomer/";
	}
	
	
	
	

}
