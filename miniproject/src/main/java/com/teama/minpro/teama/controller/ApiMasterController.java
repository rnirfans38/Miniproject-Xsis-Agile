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

import com.teama.minpro.teama.model.CustomerRelation;
import com.teama.minpro.teama.repository.CustomerRelationRepository;

@RestController
@RequestMapping("/api/")
public class ApiMasterController {

	@Autowired
	private CustomerRelationRepository customerRelationRepository;

	@GetMapping("master/customerrelation/paging")
	public ResponseEntity<Map<String, Object>> getAllCustomerRelation(@RequestParam(defaultValue = "0") int currentPage,
			@RequestParam(defaultValue = "5") int size, @RequestParam("keyword") String keyword,
			@RequestParam("sortType") String sortType) {

		try {
			List<CustomerRelation> customerRelation = new ArrayList<>();
			Pageable pagingSort = PageRequest.of(currentPage, size);

			Page<CustomerRelation> pages;
			if (sortType.equals("ASC")) {
				pages = this.customerRelationRepository.findASC(keyword, false, pagingSort);
			} else {
				pages = this.customerRelationRepository.findDESC(keyword, false, pagingSort);
			}

			customerRelation = pages.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("pages", pages.getNumber());
			response.put("total", pages.getTotalElements());
			response.put("total_pages", pages.getTotalPages());
			response.put("data", customerRelation);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("master/customerrelation/add")
	public ResponseEntity<Object> saveCustomerRelation(@RequestBody CustomerRelation customerRelation) {
		customerRelation.createdBy = (long) 1;
		customerRelation.createdOn = LocalDateTime.now();
		customerRelation.isDelete = false;

		CustomerRelation customerRelationData = this.customerRelationRepository.save(customerRelation);

		if (customerRelationData.equals(customerRelation)) {
			return new ResponseEntity<>("Simpan Berhasil", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("master/customerrelationbyid/{id}")
	public ResponseEntity<Object> getCustomerRelationById(@PathVariable("id") Long id) {
		try {
			Optional<CustomerRelation> customerRelation = this.customerRelationRepository.findById(id);
			return new ResponseEntity<>(customerRelation, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("master/customerrelation/update/{id}")
	public ResponseEntity<Object> updateCustomerRealtion(@PathVariable("id") Long id,
			@RequestBody CustomerRelation customerRelation) {
		try {
			CustomerRelation customerRelationData = this.customerRelationRepository.findById(id).orElse(null);
			customerRelationData.name = customerRelation.getName();
			customerRelationData.modifiedBy = (long) 1;
			customerRelationData.modifiedOn = LocalDateTime.now();
			this.customerRelationRepository.save(customerRelationData);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("master/customerrelation/delete/{id}")
	public ResponseEntity<Object> deleteCustomerRelationship(@PathVariable("id") Long id) {
		try {
			CustomerRelation customerRelationData = this.customerRelationRepository.findById(id).orElse(null);
			customerRelationData.isDelete = true;
			customerRelationData.deletedBy = (long) 1;
			customerRelationData.deletedOn = LocalDateTime.now();
			this.customerRelationRepository.save(customerRelationData);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("checknameisexist")
	public ResponseEntity<Object> checknameisexist(@RequestParam("name") String name, @RequestParam("id") Long id) {
		Boolean isExist = false;
		CustomerRelation customerRelation = null;
		if (id == 0) {
			customerRelation = this.customerRelationRepository.findByIdName(name);
		} else {
			customerRelation = this.customerRelationRepository.findByIdNameForEdit(name, id);
		}

		if (customerRelation != null) {
			isExist = true;
		}

		return new ResponseEntity<>(isExist, HttpStatus.OK);
	}

}
