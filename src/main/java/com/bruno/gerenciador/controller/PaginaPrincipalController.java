
package com.bruno.gerenciador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaPrincipalController {

	@GetMapping("/")
	public String viewHomePage() {;
		return "/index";
	}
	
}

