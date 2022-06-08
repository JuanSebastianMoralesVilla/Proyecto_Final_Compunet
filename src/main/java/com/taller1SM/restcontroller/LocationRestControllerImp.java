package com.taller1SM.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taller1SM.model.prod.Location;
import com.taller1SM.service.LocationService;

@RestController
public class LocationRestControllerImp implements LocationRestController {

	@Autowired
	private LocationService service;
	
	@Override
	@PostMapping("/api/templatesLocation/")
	public void save(@RequestBody Location l) {
		service.saveLocation(l);

	}

	@Override
	@RequestMapping(
			value = "/api/templatesLocation/", 
			  produces = "application/json", 
			  method=RequestMethod.PUT)
	public void edit(@RequestBody Location l) {
		service.editLocation(l);

	}



	@Override
	@GetMapping("/api/templatesLocation/{id}")
	public Location findById(@PathVariable("id") Integer id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
		
	}

	@Override
	@GetMapping("/api/templatesLocation/")
	public Iterable<Location> findAll() {
		return service.findAll();
		
	}

}
