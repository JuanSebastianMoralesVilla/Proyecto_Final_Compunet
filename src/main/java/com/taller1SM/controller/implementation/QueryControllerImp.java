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
import com.taller1SM.controller.interfaces.QueryController;
import com.taller1SM.model.sales.Store;

@Controller
public class QueryControllerImp implements QueryController{

	@Autowired
	private BusinessDelgateI businessDelegate;

	@Override
	@GetMapping("/templatesQuery/")
	public String indexQuery(Model model) {
		model.addAttribute("products", businessDelegate.findAllProduct());
		return "templatesQuery/Index";
	}
	
	@Override
	@GetMapping("/templatesQuery/one")
	public String queryOne(Model model) {
		model.addAttribute("products", businessDelegate.findByProductSumInventory_orderByLocation());
		return "templatesQuery/one";
	}
	
	@Override
	@GetMapping("/templatesQuery/two")
	public String queryTwo(Model model) {
		model.addAttribute("products", businessDelegate.findByProductCostHistoric());
		return "templatesQuery/two";
	}

	

}

