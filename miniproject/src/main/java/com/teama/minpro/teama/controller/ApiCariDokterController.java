package com.teama.minpro.teama.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teama.minpro.teama.services.CariDokterService;

@RestController
@RequestMapping("/api/caridokter/")
public class ApiCariDokterController {

	@Autowired
	private CariDokterService CDS;
	
	@GetMapping("lokasi")
	public List<Map<String, Object>>getmodalLokasi(){
		return CDS.modalLokasi();
	}
	
	@GetMapping("spesialisasi")
	public List<Map<String, Object>>getmodalSpesialisasi(){
		return CDS.modalSpesialisasi();
	}
	
	@GetMapping(value = "tindakan")
	public List<Map<String, Object>>getmodalTindakan(){
		return CDS.modalTindakan();
	}

}
