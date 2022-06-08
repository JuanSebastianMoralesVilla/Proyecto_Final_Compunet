package com.taller1SM.controller.interfaces;

import org.springframework.ui.Model;

public interface QueryController {
	public String indexQuery(Model model);
	public String queryOne(Model model);
	public String queryTwo(Model model);

}
