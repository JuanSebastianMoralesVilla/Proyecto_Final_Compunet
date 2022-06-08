package com.taller1SM.controller.implementation;


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

import com.taller1SM.businessdelegate.BusinessDelgateI;
import com.taller1SM.controller.interfaces.CustomerController;
import com.taller1SM.model.prod.Productinventory;
import com.taller1SM.model.sales.Customer;

@Controller
public class CustomerControllerImp implements CustomerController{

	
	private BusinessDelgateI businessDelegate;

	@Autowired

	public CustomerControllerImp(BusinessDelgateI customerService) {
		
		this.businessDelegate = customerService;
	}
	

	@GetMapping("/templatesCustomer/")
	public String indexCust(Model model) {
		model.addAttribute("customers", businessDelegate.findAllCustomer());
		System.out.println("ENTRE A LA PAGINA de costumers");
		return "templatesCustomer/Index";
	}
	

	@GetMapping("/templatesCustomer/add-customer")
	public String listCostumers(Model model) {
		model.addAttribute("customer", new Customer());
		
		model.addAttribute("stores",businessDelegate.findAllStore());
		return "templatesCustomer/add-customer";
	}
	
	@PostMapping("/templatesCustomer/add-customer/")
	public String saveCustomer(@Validated @ModelAttribute Customer customer, BindingResult bindingResult, Model model,
			@RequestParam(value = "action", required = true) String action) {

		if (!action.equals("Cancel")) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("stores",businessDelegate.findAllStore());
				return "templatesCustomer/add-customer";
			} 
			
		//	else if (store.getModifieddate().) {
			//	model.addAttribute("dateError", true);
			//	return "templatesProduct/add-product";
			//}
		
			businessDelegate.saveCustomer(customer);
		}
		return "redirect:/templatesCustomer/";
	}
	
	@GetMapping("/templatesCustomer/del/{id}")
	public String deleteCustomer(@PathVariable("id") Integer id, Model model) {
		Customer cust = businessDelegate.findCustomer(id);
		if (cust == null) new IllegalArgumentException("Invalid user Id:" + id);
		businessDelegate.deleteCustomer(id);
		model.addAttribute("customer", businessDelegate.findAllCustomer());
		model.addAttribute("stores",businessDelegate.findAllStore());
		return "redirect:/templatesCustomer/";
	}
	
	@GetMapping("/templatesCustomer/edit/{id}")
	public String showCustomer(@PathVariable("id") Integer id, Model model) {
	
		Customer customer = businessDelegate.findCustomer(id);
		if (customer == null)
			throw new IllegalArgumentException("Invalid customer Id:" + id);
		model.addAttribute("customer", customer);
		model.addAttribute("stores",businessDelegate.findAllStore());
		return "templatesCustomer/update-customer";
	}
	
	@PostMapping("/templatesCustomer/edit/{id}")
	public String updateCustomer(
			@PathVariable("id") Integer id, 
			@RequestParam(value = "action", required = true) String action,
			@Validated @ModelAttribute Customer cust, 
			BindingResult bindingResult,
			Model model
			) {
		if (action.equals("Cancel"))
			return "redirect:/templatesCustomer/";
		if (bindingResult.hasErrors()) {
	        
	        model.addAttribute("stores",businessDelegate.findAllStore());
	        cust.setCustomerid(id);
			return "templatesCustomer/update-customer";
		}
		if (!action.equals("Cancel")) {
			businessDelegate.editCustomer(cust);
			model.addAttribute("customers", businessDelegate.findAllCustomer());
		}
		return "redirect:/templatesCustomer/";
	}
	
	
	@GetMapping("/templatesStore/Index/{id}")
	public String showCustomertostore(@PathVariable("id") Integer id, Model model) {
		
		Customer cus= businessDelegate.findCustomer(id);
		if (cus == null)new IllegalArgumentException("Invalid product Id:" + id);
		model.addAttribute("stores", cus.getStore());
		return "templatesStore/Index";
	}
	
	

}
