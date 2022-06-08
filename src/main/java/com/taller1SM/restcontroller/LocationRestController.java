package com.taller1SM.restcontroller;

import com.taller1SM.model.prod.Location;

public interface LocationRestController {
	public void save(Location l);
	public void edit(Location l);

	public Location findById(Integer id);
	public Iterable<Location> findAll();
}
