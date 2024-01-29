package com.teama.minpro.teama.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teama.minpro.teama.model.DetailProfil;

import com.teama.minpro.teama.model.Doctor;
import com.teama.minpro.teama.model.DoctorOffice;
import com.teama.minpro.teama.repository.DetailDokterRepo;
import com.teama.minpro.teama.repository.DoctorOfficeRepository;

@RestController
@RequestMapping("/api/")
public class ApiDeDoController {

	@Autowired
	private DetailDokterRepo deDoRepo;

	@Autowired
	DoctorOfficeRepository DOR;

	//Cara dengan list map string
	@GetMapping(path = "profil/{id}")
	public List<Map<String, Object>> getProfil(@PathVariable("id") Long id) {
		try {
			return deDoRepo.profil(id);
		} catch (Exception e) {
			// Log the error or print the stack trace
			// You may also customize the error message based on the type of exception
			// For simplicity, we'll just print the stack trace here
			e.printStackTrace();

			// You can also log the error using a logging framework like SLF4J or log4j
			// log.error("An error occurred while fetching profile for id: " + id, e);

			// Return an error response or handle the exception in a way that fits your
			// application
			// For simplicity, we'll just return an empty list here
			return Collections.emptyList();
		}
	}

	//Cara membuat model baru
	@GetMapping(path = "modelProfil/{id}")
	public ResponseEntity<List<DetailProfil>> getAllDoctor(@PathVariable("id") Long id) {
		try {
			List<DetailProfil> data = this.deDoRepo.detailProfil(id);
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	
	
	
	
	
	
	
	////////////////////////////////////////////////////////////////////////////////

	// belajar a lutfi
	@GetMapping(path = "treatment/{id}")
	public List<Map<String, Object>> getTreatment(@PathVariable("id") Long id) {
		try {
			// String resutStr = new String() ;
			Object hasil = new Object();
			List<Map<String, Object>> result = deDoRepo.findTreatment(id);
			for (Object obj : result) {
				hasil = obj;
			}
//			for(int i = 0; i < result.size(); i++) {
//				resutStr = result.get(i).toString();
//			}
			return (List<Map<String, Object>>) hasil;

		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@GetMapping(path = "treatmentOriginal/{id}")
	public List<Map<String, Object>> getTreatmentOriginal(@PathVariable("id") Long id) {
		try {
			List<Map<String, Object>> result = deDoRepo.findTreatment(id);

			// Assuming you want to return the entire list of treatment maps
			return result;
		} catch (Exception e) {
			// Handle exceptions if necessary
			e.printStackTrace(); // or log the exception
			return Collections.emptyList(); // or another appropriate response
		}
	}

	@GetMapping(path = "allDoctor/{str}")
	public ResponseEntity<List<DoctorOffice>> getAllDoctor(@PathVariable("str") String str) {
		try {
			List<Doctor> listDoctor = this.deDoRepo.allDoctor(str);
			List<DoctorOffice> listDoctorOffice = this.DOR.listDoctorOffice(listDoctor.get(0).id);
			return new ResponseEntity<>(listDoctorOffice, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "dataDokter")
	public List<Map<String, Object>> getDataDokter() {

		return deDoRepo.findData();

	}

}
