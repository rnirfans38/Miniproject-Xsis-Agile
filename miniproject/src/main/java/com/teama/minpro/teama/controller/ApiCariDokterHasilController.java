package com.teama.minpro.teama.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teama.minpro.teama.repository.DoctorOfficeRepository;
import com.teama.minpro.teama.services.CariDokterHasilService;

@RestController
@RequestMapping("/api/hasil")
public class ApiCariDokterHasilController {

	@Autowired
	private CariDokterHasilService CDHS;
	
	@Autowired
	private DoctorOfficeRepository DOR;
	
	@GetMapping("alldoctorschedule/{id}")
	public List<Map<String, Object>>getallDoctorSchedule(@PathVariable Long id){
		return CDHS.allDoctorSchedule(id);
	}
	
	@GetMapping("caridokter/{lokasi}/{fullname}/{spesialis}/{tindakan}")
	public List<Map<String, Object>>getcariDokter(@PathVariable String lokasi, @PathVariable String fullname,
			@PathVariable String spesialis, @PathVariable String tindakan){
		lokasi=lokasi.equalsIgnoreCase("kosong")?"":lokasi;
		fullname=fullname.equalsIgnoreCase("kosong")?"":fullname;
		spesialis=spesialis.equalsIgnoreCase("kosong")?"":spesialis;
		tindakan=tindakan.equalsIgnoreCase("kosong")?"":tindakan; 
		return CDHS.cariDokter(lokasi, fullname, spesialis, tindakan);
	}
	//http://localhost:8080/api/hasil/caridokter2?fullname=&treatment=&location=&specialization=&currentPage=&size=
	@GetMapping("caridokter2")
	public ResponseEntity<Object> getDoctorSearchResult(@RequestParam("fullname") String fullname,
			@RequestParam("treatment") String treatment, @RequestParam("location") String location, @RequestParam("specialization") String specialization,
			@RequestParam("currentPage") int currentPage, @RequestParam("size") int size) {
		try {
			List<Map<String,Object>> listData = new ArrayList<>();
			Pageable pageable = PageRequest.of(currentPage, size);

			List<Map<String,Object>> distinctResults = this.DOR.searchDoctorPage(specialization, location,
					treatment, fullname, pageable);
			
			List<Map<String,Object>> doctorResults = this.DOR.searchDoctor(specialization, location, treatment, fullname);

			System.out.println("TEST SIZE DISTINCT RESULTS : " + distinctResults);
			System.out.println("TEST SIZE DISTINCT RESULTS Size : " + distinctResults.size());
			System.out.println("TEST SIZE Doctor RESULTS Size : " + doctorResults.size());

			Page<Map<String,Object>> pages = new PageImpl<>(distinctResults, pageable, doctorResults.size());

			System.out.println("TEST PAGES : " + pages);
			listData = pages.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("pages", pages.getNumber());
			response.put("total", pages.getTotalElements());
			response.put("total_pages", pages.getTotalPages());
			response.put("data", listData);

			System.out.println("TEST::" + response);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	// list map untuk mengisi data yang berbeda
	
	
}
