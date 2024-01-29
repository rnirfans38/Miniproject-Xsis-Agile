package com.teama.minpro.teama.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teama.minpro.teama.model.MedicalFacilityCategory;
import com.teama.minpro.teama.repository.MedicalRepository;

@RestController
@RequestMapping("/api/")
public class ApiMedicalController {

	@Autowired
	private MedicalRepository medicalRepository;

	@GetMapping("medical")
	public ResponseEntity<List<MedicalFacilityCategory>> getAllMedical(@RequestParam("name") String name) { // Tergantung
																											// data,
																											// kalo list
																											// untuk
																											// banyak

		try {
			List<MedicalFacilityCategory> listMedical = this.medicalRepository.findByIsDelete();
			return new ResponseEntity<>(listMedical, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}
	}

	// PAGINATION
	@GetMapping("medical/paging")
	public ResponseEntity<Map<String, Object>> getAllMedicalPages(@RequestParam(defaultValue = "0") int currentPage,
			@RequestParam(defaultValue = "5") int size, @RequestParam("keyword") String keyword,
			@RequestParam("sortType") String sortType) {

		try {
			List<MedicalFacilityCategory> medical = new ArrayList<>();
			Pageable pagingSort = PageRequest.of(currentPage, size);

			Page<MedicalFacilityCategory> pages;

			if (sortType.equals("ASC")) {
				pages = this.medicalRepository.findByIsDeleteASC(keyword, false, pagingSort);
			} else {
				pages = this.medicalRepository.findByIsDeleteDESC(keyword, false, pagingSort);
			}

			medical = pages.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("pages", pages.getNumber());
			response.put("total", pages.getTotalElements());
			response.put("total_pages", pages.getTotalPages());
			response.put("data", medical); // list ada disini

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("medical/add")
	public ResponseEntity<Object> saveMedical(@RequestBody MedicalFacilityCategory medical) {
		medical.createdBy = (long)1;
		medical.createdOn = LocalDateTime.now();
		medical.isDelete = false;

		MedicalFacilityCategory medicalData = this.medicalRepository.save(medical);

		if (medicalData.equals(medical)) {
			return new ResponseEntity<>("Save Data Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Save Failed", HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("medical/{id}")
	public ResponseEntity<Object> getMedicalById(@PathVariable("id") Long id) {
		try {
			Optional<MedicalFacilityCategory> medical = this.medicalRepository.findById(id);
			return new ResponseEntity<>(medical, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("medical/edit/{id}")
	public ResponseEntity<Object> editCourier(@PathVariable("id") Long id,
			@RequestBody MedicalFacilityCategory medical) {
		Optional<MedicalFacilityCategory> medicalData = this.medicalRepository.findById(id);

		if (medicalData.isPresent()) {
			medical.id = id;
			medical.modifiedBy = (long) 1;
			medical.modifiedOn = LocalDateTime.now();
			medical.isDelete = false;
			medical.createdBy = medicalData.get().createdBy;
			medical.createdOn = medicalData.get().createdOn;

			this.medicalRepository.save(medical);
			return new ResponseEntity<>("Updated Data Success", HttpStatus.OK);

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("medical/delete/{id}")
	public ResponseEntity<Object> deleteMedical(@PathVariable("id") Long id) {
		Optional<MedicalFacilityCategory> medicalData = this.medicalRepository.findById(id);

		if (medicalData.isPresent()) {
			MedicalFacilityCategory medical = new MedicalFacilityCategory();
			medical.id = id;
			medical.name = medicalData.get().name;
			medical.createdBy = medicalData.get().createdBy;
			medical.createdOn = medicalData.get().createdOn;
			medical.modifiedBy = medicalData.get().modifiedBy;
			medical.modifiedOn = medicalData.get().modifiedOn;
			medical.deletedBy = (long)1;
			medical.deletedOn = LocalDateTime.now();
			medical.isDelete = true;

			this.medicalRepository.save(medical);
			return new ResponseEntity<>("Deleted Data Success", HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("checknamemedical")
	public ResponseEntity<Object> checknamemedical(@RequestParam("name") String name, @RequestParam("id") Long id) {
		Boolean isExist = false;
		MedicalFacilityCategory medical = null;
		if (id == 0) {
			medical = this.medicalRepository.findByIdName(name);
		} else {
			medical = this.medicalRepository.findByIdNameForEdit(name, id);
		}

		if (medical != null) {
			isExist = true;
		}
		return new ResponseEntity<>(isExist, HttpStatus.OK);
	}

	@GetMapping("diubahOleh")
	public List<Map<String, Object>> diubahOleh(@PathVariable Long id) {
		return medicalRepository.diubahOleh(id);
	}

}
