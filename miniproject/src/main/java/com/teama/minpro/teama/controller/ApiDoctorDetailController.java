package com.teama.minpro.teama.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teama.minpro.teama.services.DetailDokterService;


@RestController
@RequestMapping("/api/detail/")
public class ApiDoctorDetailController {
	@Autowired
	private DetailDokterService dds;

	@GetMapping(value ="pendidikan/{id}" )
	public List<Map<String, Object>>pendidikan(@PathVariable Long id){
		return dds.pendidikan(id);
	}

	@GetMapping(value ="riwayat/{id}" )
	public List<Map<String, Object>>getlistRiwayat(@PathVariable Long id){
		return dds.riwayat(id);
	}	


	@GetMapping(value ="nama/{id}" ) 
	public Map<String, Object>nama(@PathVariable Long id){
		return dds.nama(id);
	}

	@GetMapping(value ="tindakan/{id}" )
	public List<Map<String, Object>> tindakan(@PathVariable("id") Long id){
		return dds.tindakan(id);
	}

	@GetMapping(value ="lokasi/{id}" )
	public List<Map<String, Object>>listdetail(@PathVariable Long id){
		return dds.listdetail(id);
	}


	@GetMapping(value ="online/{id}" )
	public List<Map<String, Object>>listdetailonline(@PathVariable Long id){
		return dds.listdetailonline(id);
	}


	@GetMapping(value ="pengalaman/{doctor_id}" )
	public Map<String, Object>pengalaman(@PathVariable Long doctor_id){
		return dds.pengalaman(doctor_id);
	}


}
