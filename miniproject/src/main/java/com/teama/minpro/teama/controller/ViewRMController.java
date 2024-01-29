package com.teama.minpro.teama.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/rm/")
public class ViewRMController {
	
	@GetMapping("mitoha")
	public ModelAndView functionName() {
		ModelAndView view = new ModelAndView("rumahmakan/rumahmakan.html");
		return view; //objek(view
	}

}
