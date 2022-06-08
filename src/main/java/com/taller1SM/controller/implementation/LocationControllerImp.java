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
import com.taller1SM.controller.interfaces.LocationController;
import com.taller1SM.model.prod.Location;

@Controller
public class LocationControllerImp implements LocationController{

	
	BusinessDelgateI businessDelegate;
	
	
	
	
	@Autowired
	public LocationControllerImp(BusinessDelgateI locationService) {
		
		this.businessDelegate = locationService;
	}


	@GetMapping("/templatesLocation/")
	public String indexLocation(Model model) {
		model.addAttribute("locations", businessDelegate.findAllLocation());

		return "templatesLocation/Index";
	}
	
	@GetMapping("/templatesLocation/add-location")
	public String listProduct(Model model) {
		model.addAttribute("location", new Location());
		return "templatesLocation/add-location";
	}

	
	
	
	@PostMapping("/templatesLocation/add-location/")
	public String saveLocation(@Validated @ModelAttribute Location location,
			BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		
		if (action.equals("Cancel"))
			return "redirect:/templatesLocation/";

		if (bindingResult.hasErrors()) {
		
			return "templatesLocation/add-location";
		}
		if (!action.equals("Cancel")) {	
		
			businessDelegate.saveLocation(location);
		}
		System.out.println("EXITO add locations");
		return "redirect:/templatesLocation/";
	}
	
	@GetMapping("/templatesLocation/edit/{id}")
	public String showUpdateLocation(@PathVariable("id") Integer id, Model model) {
	
		Location location = businessDelegate.findLocation(id);
		if (location == null)
			throw new IllegalArgumentException("Invalid location Id:" + id);
		model.addAttribute("location", location);

		return "templatesLocation/update-location";
	}

	@PostMapping("/templatesLocation/edit/{id}")
	public String updateLocation(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action,
			@Validated @ModelAttribute Location location, BindingResult bindingResult, Model model) {
		
		
		if (bindingResult.hasErrors()) {
			location.setLocationid(id);
			return "templatesLocation/update-location";
		}
		if (!action.equals("Cancel")) {
			location.setLocationid(id);
			businessDelegate.editLocation(location);
			model.addAttribute("locations", businessDelegate.findAllLocation());
		}
		return "redirect:/templatesLocation/";
	}
	
	
	

	
	@GetMapping("/templatesProductInventory/Index/{id}")
	public String showLocation(@PathVariable("id") Integer id, Model model) {
		
	//Location l= businessDelegate.findLocation(id);
      // if (l == null) new IllegalArgumentException("Invalid location:" + id);
		
		
		
		Location l= businessDelegate.findLocation(id);
	       
		model.addAttribute("productinventory", l.getProductinventories());
		return "templatesProductInventory/Index";
	}

}
