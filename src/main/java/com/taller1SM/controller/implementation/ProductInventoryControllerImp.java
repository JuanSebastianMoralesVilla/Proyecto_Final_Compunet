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
import com.taller1SM.controller.interfaces.ProductInventoryController;
import com.taller1SM.model.prod.Productinventory;

@Controller
public class ProductInventoryControllerImp implements ProductInventoryController {

	// relaciones de product inventory
	@Autowired
	private BusinessDelgateI businessDelegate;



	@GetMapping("/templatesProductInventory/")
	public String indexpir(Model model) {

		model.addAttribute("productinventory", businessDelegate.findAllPIR());
		return "templatesProductInventory/Index";

	}

	@GetMapping("/templatesProductInventory/add-productInventory")
	public String addpir(Model model) {

		model.addAttribute("productinventory", new Productinventory());
		model.addAttribute("products", businessDelegate.findAllProduct());
		model.addAttribute("locations", businessDelegate.findAllLocation());

		return "templatesProductInventory/add-productInventory";

	}

	@PostMapping("/templatesProductInventory/add-productInventory/")
	public String saveInventory(@Validated @ModelAttribute Productinventory productinventory,
			BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {

		if (action.equals("Cancel")) {
			return "redirect:/templatesProductInventory/";
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("products", businessDelegate.findAllProduct());
			model.addAttribute("locations", businessDelegate.findAllLocation());
			return "/templatesProductInventory/add-productInventory";
		}

		if (!action.equals("Cancel")) {
			businessDelegate.savePIR(productinventory);
		}

		return "redirect:/templatesProductInventory/";
	}

	@GetMapping("/templatesProductInventory/edit/{id}")
	public String showUpdateInventory(@PathVariable("id") Integer id, Model model) {
		Productinventory productinventory = businessDelegate.findPIR(id);
		if (productinventory == null)
			throw new IllegalArgumentException("Invalid  Id:" + id);
		model.addAttribute("productinventory", productinventory);
		model.addAttribute("products", businessDelegate.findAllProduct());
		model.addAttribute("locations", businessDelegate.findAllLocation());
		return "templatesProductInventory/update-productInventory";
	}

	@PostMapping("/templatesProductInventory/edit/{id}")
	public String updateInventory(@PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action,
			@Validated @ModelAttribute Productinventory productinventory, BindingResult bindingResult, Model model) {
		if (action.equals("Cancel"))
			return "redirect:/templatesProductInventory/";
		if (bindingResult.hasErrors()) {
			model.addAttribute("products", businessDelegate.findAllProduct());
			model.addAttribute("locations", businessDelegate.findAllLocation());
			productinventory.setId(id);
			return "templatesProductInventory/update-productInventory";
		}
		if (!action.equals("Cancel")) {
			businessDelegate.editPIR(productinventory);
			model.addAttribute("products", businessDelegate.findAllProduct());
		}
		return "redirect:/templatesProductInventory/";
	}
	
	
	@GetMapping("/templatesProduct2/Index/{id}")
	public String showProducttoPIR(@PathVariable("id") Integer id, Model model) {
		
		Productinventory pir= businessDelegate.findPIR(id);
		if (pir == null)new IllegalArgumentException("Invalid product Id:" + id);
		model.addAttribute("products", pir.getProduct());
		return "templatesproduct/Index";
	}
	
	@GetMapping("/templatesLocation/Index/{id}")
	public String showLocationtoPIR(@PathVariable("id") Integer id, Model model) {
		
		Productinventory pir= businessDelegate.findPIR(id);
		if (pir == null)new IllegalArgumentException("Invalid product Id:" + id);
		model.addAttribute("locations", pir.getLocation());
		return "templatesLocation/Index";
	}


}
