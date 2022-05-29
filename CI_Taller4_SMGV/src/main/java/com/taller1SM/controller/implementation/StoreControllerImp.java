package com.taller1SM.controller.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taller1SM.controller.interfaces.StoreController;
import com.taller1SM.model.prod.Product;
import com.taller1SM.model.sales.Store;
import com.taller1SM.service.ProductService;
import com.taller1SM.service.StoreService;

@Controller
public class StoreControllerImp  implements StoreController{

	

	private StoreService StoreService;

	@Autowired

	public StoreControllerImp(com.taller1SM.service.StoreService storeService) {
		
		StoreService = storeService;
	}

	@GetMapping("/templatesStore/")
	public String indexStore(Model model) {
		model.addAttribute("stores", StoreService.findAll());
		System.out.println("ENTRE A LA PAGINA");
		return "tempLatesStore/Index";
	}


	@GetMapping("/templatesStore/add-store")
	public String listProduct(Model model) {
		model.addAttribute("store", new Store());
		

		return "templatesStore/add-store";
	}

	@PostMapping("/templatesStore/add-store/")
	public String saveProduct(@Validated @ModelAttribute Store store, BindingResult bindingResult, Model model,
			@RequestParam(value = "action", required = true) String action) {

		if (!action.equals("Cancel")) {
			//model.addAttribute("subcategories", productService.findAllSubcategory());
		if (bindingResult.hasErrors()) {
				return "templatesStore/add-store";
			} 
			
			//else if (store.getModifieddate().isBefore()) {
			//	model.addAttribute("dateError", true);
			//	return "templatesProduct/add-product";
			//}
		
			StoreService.saveStore(store);
		}
		return "redirect:/templatesStore/";
	}
	
	
	
	
	
	
	
	
	
	
	
}

