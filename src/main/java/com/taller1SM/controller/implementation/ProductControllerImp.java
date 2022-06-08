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
import com.taller1SM.controller.interfaces.ProductController;
import com.taller1SM.model.prod.Product;
import com.taller1SM.repositories.ProductSubcategoryRepository;

@Controller

public class ProductControllerImp implements ProductController {

	private BusinessDelgateI businessDelegate;
	private ProductSubcategoryRepository repo;

	@Autowired
	public ProductControllerImp(BusinessDelgateI productService, ProductSubcategoryRepository repo) {
		this.repo = repo;
		this.businessDelegate = productService;
	}

	@GetMapping("/templatesProduct/")
	public String indexProduct(Model model) {
		model.addAttribute("products", businessDelegate.findAllProduct());
		System.out.println("ENTRE A LA PAGINA");
		return "tempLatesProduct/Index";
	}

	@GetMapping("/templatesProduct/add-product")
	public String listProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("subcategories", repo.findAll());
		System.out.println("subcategorias agregadas");

		return "templatesProduct/add-product";
	}

	@PostMapping("/templatesProduct/add-product/")
	public String saveProduct(@Validated @ModelAttribute Product product, BindingResult bindingResult, Model model,
			@RequestParam(value = "action", required = true) String action) {

		if (!action.equals("Cancel")) {
			model.addAttribute("subcategories", repo.findAll());
			if (bindingResult.hasErrors()) {
				return "templatesProduct/add-product";
			} else if (product.getSellenddate().isBefore(product.getSellstartdate())) {
				model.addAttribute("dateError", true);
				return "templatesProduct/add-product";
			}
			businessDelegate.saveProduct(product);
		}
		return "redirect:/templatesProduct/";
	}
	
	@GetMapping("/templatesProduct/edit/{id}")
	public String showUpdateProduct(@PathVariable("id") Integer id, Model model) {
		Product product = businessDelegate.findProduct(id);
		if (product == null)
			throw new IllegalArgumentException("invalide Id:" + id);
		model.addAttribute("product", product);
		model.addAttribute("subcategories", repo.findAll());
		return "templatesProduct/update-product";
	}

	@PostMapping("/templatesProduct/edit/{id}")
	public String updateProduct(
			@PathVariable("id") Integer id, 
			@RequestParam(value = "action", required = true) String action,
			@Validated @ModelAttribute Product product, 
			BindingResult bindingResult,
			Model model
			) {
		if (action.equals("Cancel"))
			return "redirect:/templatesProduct/";
		if (product.getSellstartdate() != null && product.getSellenddate() != null) {
			if (product.getSellstartdate().isAfter(product.getSellenddate())) {
				bindingResult.addError(
						// mensaje de error mediante campo de error mismo nombre en el html
						new FieldError("product", "sellstartdate", "La fecha de inicio de venta debe ser menor a la fecha final de venta."));
			}
		}
		if (bindingResult.hasErrors()) {
			model.addAttribute("subcategories", repo.findAll());
			product.setProductid(id);
			return "templatesProduct/update-product";
		}
		if (!action.equals("Cancel")) {
			product.setProductid(id);
			businessDelegate.editProduct(product);
			model.addAttribute("products", businessDelegate.findAllProduct());
		}
		return "redirect:/templatesProduct/";
	}
	
	
	
	
	// consultas producto tiene asociado una categoria
	
	
	@GetMapping("/templatesproduct/consultasubcategory/{id}")
	public String showProducts(@PathVariable("id") Integer id, Model model) {
		
		//Product p = productService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
		model.addAttribute("products", businessDelegate.findAllProduct());
		model.addAttribute("subcategories", repo.findAll());
		return "templatesproduct/consultasubcategory";
	}
	
	
	
	@GetMapping("/templatesProductCostHistoric/Index/{id}")
	public String showProductsCostHistoric(@PathVariable("id") Integer id, Model model) {
		
		Product p= businessDelegate.findProduct(id);
		if (p == null) throw new IllegalArgumentException("invalide Id:" + id);
		model.addAttribute("productcosthistory", p.getProductcosthistories());
		return "templatesProductCostHistoric/Index";
	}

	

	@GetMapping("/templatesProduct/Index/{id}")
	public String showProductsInventory(@PathVariable("id") Integer id, Model model) {
		
		Product p= businessDelegate.findProduct(id);
		if (p == null) throw new IllegalArgumentException("invalide Id:" + id);
		model.addAttribute("productinventory", p.getProductinventories());
		return "templatesProductInventory/Index";
	}
}

