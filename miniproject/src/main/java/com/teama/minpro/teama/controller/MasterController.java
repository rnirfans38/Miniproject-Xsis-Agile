package com.teama.minpro.teama.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/master/")
public class MasterController {
	@GetMapping("hubunganpasien")

	public ModelAndView hubunganPasien() {
		ModelAndView view = new ModelAndView("master/hubunganpasien.html");
		return view;
	}

	@GetMapping("profilshared")
	public ModelAndView profilshared() {
		ModelAndView view = new ModelAndView("master/profilshared.html");
		return view;
	}

	@GetMapping("bank")
	public ModelAndView bank() {
		ModelAndView view = new ModelAndView("master/bank");
		return view;
	}
}
