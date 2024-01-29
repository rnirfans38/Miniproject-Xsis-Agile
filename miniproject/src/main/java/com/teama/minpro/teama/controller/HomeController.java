package com.teama.minpro.teama.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	@GetMapping("index")
	public String index() {
		return "index.html";
	}
	
	@GetMapping("dashboard")
	public String dashboard() {
		return "dashboard.html";
	}
	

	
}
