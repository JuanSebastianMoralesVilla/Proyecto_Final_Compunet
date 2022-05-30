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
import com.taller1SM.model.prod.Location;
import com.taller1SM.model.prod.Product;
import com.taller1SM.model.sales.Store;
import com.taller1SM.service.ProductService;
import com.taller1SM.service.StoreService;


@Controller
public class StoreControllerImp  implements StoreController{

	

	private StoreService storeService;

	@Autowired

	public StoreControllerImp(com.taller1SM.service.StoreService storeService) {
		
		this.storeService = storeService;
	}

	@GetMapping("/templatesStore/")
	public String indexStore(Model model) {
		model.addAttribute("stores", storeService.findAll());
		System.out.println("ENTRE A LA PAGINA");
		return "tempLatesStore/Index";
	}


	@GetMapping("/templatesStore/add-store")
	public String listStores(Model model) {
		model.addAttribute("store", new Store());
		

		return "templatesStore/add-store";
	}
	
	
	@GetMapping("/templatesStore/del/{id}")
	public String deleteStore(@PathVariable("id") Integer id, Model model) {
		Store store = storeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		storeService.delete(store);
		model.addAttribute("store", storeService.findAll());
		return "tempLatesStore/Index";
	}

	@PostMapping("/templatesStore/add-store/")
	public String saveProduct(@Validated @ModelAttribute Store store, BindingResult bindingResult, Model model,
			@RequestParam(value = "action", required = true) String action) {

		if (!action.equals("Cancel")) {
		if (bindingResult.hasErrors()) {
				return "templatesStore/add-store";
			} 
			
		//	else if (store.getModifieddate().) {
			//	model.addAttribute("dateError", true);
			//	return "templatesProduct/add-product";
			//}
		
			storeService.saveStore(store);
		}
		return "redirect:/templatesStore/";
	}
	
	@GetMapping("/templatesStore/edit/{id}")
	public String showStore(@PathVariable("id") Integer id, Model model) {
	
		Optional<Store> store = storeService.findById(id);
		if (store == null)
			throw new IllegalArgumentException("Invalid location Id:" + id);
		model.addAttribute("store", store.get());

		return "templatesStore/update-store";
	}

	@PostMapping("/templatesStore/edit/{id}")
	public String updateStore(
			@PathVariable("id") Integer id, 
			@RequestParam(value = "action", required = true) String action,
			@Validated @ModelAttribute Store store, 
			BindingResult bindingResult,
			Model model
			) {
		if (action.equals("Cancel"))
			return "redirect:/templatesStore/";
		if (bindingResult.hasErrors()) {
	store.setBusinessentityid(id);
			return "templatesStore/update-store";
		}
		if (!action.equals("Cancel")) {
			storeService.editStore(id, store);
			model.addAttribute("stores", storeService.findAll());
		}
		return "redirect:/templatesStore/";
	}
	
	
	
	
	
	
	
	
	
	
	
}

