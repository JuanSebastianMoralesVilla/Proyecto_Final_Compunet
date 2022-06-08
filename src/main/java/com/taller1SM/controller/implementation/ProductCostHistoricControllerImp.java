package com.taller1SM.controller.implementation;

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

import com.taller1SM.businessdelegate.BusinessDelgateI;
import com.taller1SM.controller.interfaces.ProductCosthistoricController;
import com.taller1SM.model.prod.Productcosthistory;

@Controller
public class ProductCostHistoricControllerImp implements ProductCosthistoricController {

	private BusinessDelgateI businessDelegate;

	@Autowired
	public ProductCostHistoricControllerImp(BusinessDelgateI pcostHistory) {
		this.businessDelegate = pcostHistory;
	}

	@GetMapping("/templatesProductCostHistoric/")
	public String indexpchs(Model model) {
		model.addAttribute("productcosthistory", businessDelegate.findAllsPHC());

		return "tempLatesProductCostHistoric/Index";
	}

	@GetMapping("/templatesProductCostHistoric/add-productCostHistoric")
	public String listpchs(Model model) {
		model.addAttribute("productcosthistory", new Productcosthistory());
		model.addAttribute("products", businessDelegate.findAllProduct());
		return "templatesProductCostHistoric/add-productCostHistoric";
	}

	@PostMapping("/templatesProductCostHistoric/add-productCostHistoric/")
	public String savePCosthistoric(@Validated @ModelAttribute Productcosthistory productcosthistory,
			BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {

		if (action.equals("Cancel")) {
			System.out.println("salIo de HC");
			return "redirect:/templatesProductCostHistoric/";
		}

		if (productcosthistory.getModifieddate() != null && productcosthistory.getEnddate() != null) {

			if (!(productcosthistory.getModifieddate().isBefore(productcosthistory.getEnddate()))) {
				bindingResult.addError(
						new FieldError("productcosthistory", "modifieddate", "Corrige la fecha de modificacion."));
			}
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("productcosthistory", productcosthistory);
			model.addAttribute("products", businessDelegate.findAllProduct());

			return "templatesProductCostHistoric/add-productCostHistoric";
		}

		if (!action.equals("Cancel")) {
			businessDelegate.savesPHC(productcosthistory);
		}
		return "redirect:/templatesProductCostHistoric/";
	}
	
	
	@GetMapping("/templatesProductCostHistoric/edit/{id}")
	public String showUpdateCosthistory(@PathVariable("id") Integer id, Model model) {
		Productcosthistory productcosthistory = businessDelegate.findsPHC(id);
		if (productcosthistory == null) throw new IllegalArgumentException("Invalid location Id:" + id);
		model.addAttribute("productcosthistory", productcosthistory);
		model.addAttribute("products", businessDelegate.findAllProduct());
		return "templatesProductCostHistoric/update-productCostHistoric";
	}

	
	

	@PostMapping("/templatesProductCostHistoric/edit/{id}")
	public String updateCosthistory(
			@PathVariable("id") Integer id, 
			@RequestParam(value = "action", required = true) String action,
			@Validated @ModelAttribute Productcosthistory productcosthistory, 
			BindingResult bindingResult,
			Model model) {
		if (action.equals("Cancel"))
			return "redirect:/templatesProductCostHistoric/";
		if (productcosthistory.getModifieddate() != null && productcosthistory.getEnddate () != null) {
			if (productcosthistory.getModifieddate().isAfter(productcosthistory.getEnddate())) {
			bindingResult.addError(
					new FieldError("productcosthistory", "modifieddate", "La fecha de modificación debe ser antes de la fecha de fin."));
			}
		}
		if (bindingResult.hasErrors()) {
			model.addAttribute("products", businessDelegate.findAllProduct());
			productcosthistory.setId(id);
			return "templatesProductCostHistoric/update-productCostHistoric";
		}
		if (!action.equals("Cancel")) {
		businessDelegate.editsPHC(productcosthistory);
		}
		return "redirect:/templatesProductCostHistoric/";
	}
	
	// cosyt historic asociado a un producto
	

	
	
	
	@GetMapping("templatesproduct/Index/{id}")
	public String showProduct(@PathVariable("id") Integer id, Model model) {
		
		Productcosthistory p= businessDelegate.findsPHC(id);
		if (p == null) throw new IllegalArgumentException("Invalid location Id:" + id);
		model.addAttribute("products", p.getProduct());
		return "templatesProduct/Index";
	}


}
