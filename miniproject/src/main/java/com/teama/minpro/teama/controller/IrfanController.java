package com.teama.minpro.teama.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/irfan/")
public class IrfanController {

	@GetMapping("coba")
	public ModelAndView coba() {
		ModelAndView view = new ModelAndView("courier/indexapi_pg.html");
		return view;
	}
	
	@GetMapping("m_courier")
	public ModelAndView fungsiCari() {
		ModelAndView view = new ModelAndView("courier/m_courier.html");
		return view;
	}
	
	@GetMapping("m_medical")
	public ModelAndView fungsiCari2() {
		ModelAndView view = new ModelAndView("medicalfacilitycategory/m_medicalfacilitycategory.html");
		return view;
	}
}
