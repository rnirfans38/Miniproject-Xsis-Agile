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

import com.teama.minpro.teama.model.Bank;
import com.teama.minpro.teama.repository.BankRepository;

@RestController
@RequestMapping("/api/")
public class ApiBankController {

	@Autowired
	private BankRepository bankRepository;

	@GetMapping(value = "allDataBank")
	public ResponseEntity<List<Bank>> getAllDataBankEntity() {
		try {
			List<Bank> listAllDataBank = this.bankRepository.findByIsDelete();
			return new ResponseEntity<>(listAllDataBank, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("bank")
	public ResponseEntity<List<Bank>> getAllBank(@RequestParam("name") String name) {
		try {
			List<Bank> listBank = this.bankRepository.findByIsDelete();
			return new ResponseEntity<>(listBank, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	// PAGINATION
	// TEMBAK API =
	// http://localhost:8080/api/bankdata/paging?currentPage=0&size=5&keyword=Bank%20Central%20Indonesia&sortType=ASC
	@GetMapping("bank/paging")
	public ResponseEntity<Map<String, Object>> getBankPaging(@RequestParam(defaultValue = "0") int currentPage,
			@RequestParam(defaultValue = "5") int size, @RequestParam("keyword") String keyword,
			@RequestParam("sortType") String sortType) {

		try {
			List<Bank> bankdata = new ArrayList<>();
			Pageable pagingSort = PageRequest.of(currentPage, size);

			Page<Bank> pages;

			if (sortType.equals("ASC")) {
				pages = this.bankRepository.dataASC(keyword, false, pagingSort, sortType);
			} else {
				pages = this.bankRepository.dataDESC(keyword, false, pagingSort, sortType);
			}

			bankdata = pages.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("pages", pages.getNumber());
			response.put("total", pages.getTotalElements());
			response.put("total_pages", pages.getTotalPages());
			response.put("databank", bankdata);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Post = menambahkan data baru ke database dengan dalam kurung url
	@PostMapping(value = "bank/addData")
	public ResponseEntity<Object> saveData(@RequestBody Bank bank) { // save data dengan mengirimkan form (requestbody)
		bank.createdBy = (long) 1;
		bank.createdOn = LocalDateTime.now();
		bank.isDelete = false;

		Bank bankData = this.bankRepository.save(bank);

		if (bankData.equals(bank)) {
			return new ResponseEntity<>("Save Bank Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Save Failed", HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(value = "bank/{id}")
	public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
		try {
			Optional<Bank> bank = this.bankRepository.findByIdd(id);
			return new ResponseEntity<>(bank, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping(value = "bank/edit/{id}")
	public ResponseEntity<Object> editDataById(@PathVariable("id") Long id, @RequestBody Bank bank) {
		// Get data by id
		Optional<Bank> bankData = this.bankRepository.findById(id);
		
		// Memeriksa apakah data bank by id
		if(bankData.isPresent()) {
			//update setiap column
			bank.modifiedBy = (long)1;
			bank.modifiedOn = LocalDateTime.now();
			bank.isDelete = bankData.get().isDelete; //get data yang sudah ada
			bank.createdBy = bankData.get().createdBy;
			bank.createdOn = bankData.get().createdOn;
			bank.id = bankData.get().getId();
			
		// Menyimpan data yang di update ke repositori
			this.bankRepository.save(bank);
			
		//Mengembalikan respon sukses
			return new ResponseEntity<>("Update Success", HttpStatus.OK);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping(path = "bank/delete/{id}")
	public ResponseEntity<Object> deleteBank(@PathVariable("id") Long id) {
		Optional<Bank> bankData = this.bankRepository.findByIdd(id);
		
		if (bankData.isPresent()) {
			Bank bank = new Bank();
			bank.id = bankData.get().id;
			bank.name = bankData.get().name;
			bank.vaCode = bankData.get().vaCode;
			bank.createdBy = bankData.get().createdBy;
			bank.createdOn = bankData.get().createdOn;
			bank.modifiedBy = bankData.get().modifiedBy;
			bank.modifiedOn = bankData.get().modifiedOn;
			bank.deletedBy = (long) 1;
			bank.deletedOn = LocalDateTime.now();
			bank.isDelete = true;
			
			this.bankRepository.save(bank);
			return new ResponseEntity<>("Deleted Success", HttpStatus.OK);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	

}
