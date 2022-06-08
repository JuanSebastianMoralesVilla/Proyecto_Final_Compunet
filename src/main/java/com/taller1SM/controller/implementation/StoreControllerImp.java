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
import com.taller1SM.controller.interfaces.StoreController;
import com.taller1SM.model.sales.Store;

@Controller
public class StoreControllerImp implements StoreController {

	@Autowired
	private BusinessDelgateI businessDelegate;

	@GetMapping("/templatesStore/")
	public String indexStore(Model model) {
		model.addAttribute("stores", businessDelegate.findAllStore());
		System.out.println("ENTRE A LA PAGINA");
		return "templatesStore/Index";
	}

	@GetMapping("/templatesStore/add-store")
	public String listStores(Model model) {
		model.addAttribute("store", new Store());
model.addAttribute("stores",businessDelegate.findAllStore());
		return "templatesStore/add-store";
	}

	@GetMapping("/templatesStore/del/{id}")
	public String deleteStore(@PathVariable("id") Integer id, Model model) {
		Store store = businessDelegate.findStore(id);
		if (store == null)
			new IllegalArgumentException("Invalid user Id:" + id);
		businessDelegate.deleteStore(store.getBusinessentityid());
		//model.addAttribute("store", businessDelegate.findAllStore());
		return "redirect:/templatesStore/";
	}

	@PostMapping("/templatesStore/add-store/")
	public String saveProduct(@Validated @ModelAttribute Store store, BindingResult bindingResult, Model model,
			@RequestParam(value = "action", required = true) String action) {

		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "templatesStore/add-store";
			}

			// else if (store.getModifieddate().) {
			// model.addAttribute("dateError", true);
			// return "templatesProduct/add-product";
			// }

			businessDelegate.saveStore(store);
		}
		return "redirect:/templatesStore/";
	}

	@GetMapping("/templatesStore/edit/{id}")
	public String showStore(@PathVariable("id") Integer id, Model model) {

		Store store = businessDelegate.findStore(id);
		if (store == null)
			throw new IllegalArgumentException("Invalid store Id:" + id);
		model.addAttribute("store", store);

		return "templatesStore/update-store";
	}

	@PostMapping("/templatesStore/edit/{id}")
	public String updateStore(@PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action, @Validated @ModelAttribute Store store,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {

			return "templatesStore/update-store";
		}
		if (!action.equals("Cancel")) {
			store.setBusinessentityid(id);
			businessDelegate.editStore(store);
			model.addAttribute("stores", businessDelegate.findAllStore());
		}
		return "redirect:/templatesStore/";

	}

}
