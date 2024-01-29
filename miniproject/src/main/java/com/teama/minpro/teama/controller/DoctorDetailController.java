package com.teama.minpro.teama.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/doctor/")
public class DoctorDetailController {

	@GetMapping("cari")
	public ModelAndView indexapi() {
		ModelAndView view = new ModelAndView("doctor/cari.html");
		return view;
	}
	
	@GetMapping("detail2")
	public ModelAndView hasilCari() {
		ModelAndView view = new ModelAndView("doctor/detail2.html");
		return view;
	}
	
	@GetMapping("{id}")
	public ModelAndView detailDokterView(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("doctor/detail2.html");
		view.addObject("abc",id);
		return view;
	}
	@GetMapping("cari2")
	public ModelAndView indexapi3() {
		ModelAndView view = new ModelAndView("doctor/cari2.html");
		return view;
	}
	
	@GetMapping("cari-test")
	public ModelAndView indexapi4() {
		ModelAndView view = new ModelAndView("doctor/cari-test.html");
		return view;
	}

}
