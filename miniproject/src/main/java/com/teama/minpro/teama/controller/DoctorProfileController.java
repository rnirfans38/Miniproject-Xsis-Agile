package com.teama.minpro.teama.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/profil")
public class DoctorProfileController {
	
	@GetMapping("/doctor")
	public ModelAndView profile() {
		ModelAndView view = new ModelAndView("doctor/profile.html");
		return view;
	}
}
