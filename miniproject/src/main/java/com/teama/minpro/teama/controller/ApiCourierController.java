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

import com.teama.minpro.teama.model.Courier;
import com.teama.minpro.teama.repository.CourierRepository;

@RestController /* Anotasi_restful controller */
@RequestMapping("/api/")
public class ApiCourierController {

	@Autowired
	private CourierRepository courierRepository;

//	@GetMapping("courier")
//	public ResponseEntity<List<Courier>> getAllCourier() { // Tergantung data, kalo
//															// list untuk banyak
//
//		try {
//			// List<Category> listCategory = this.categoryRepsoitory.findAll();
//			List<Courier> listCourier = this.courierRepository.findByIsDelete();
//			return new ResponseEntity<>(listCourier, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//		}
//	}

	// PAGINATION
	@GetMapping("courier/paging") // Get = menampilkan
	public ResponseEntity<Map<String, Object>> getAllCourierPages(@RequestParam(defaultValue = "0") int currentPage,
			@RequestParam(defaultValue = "5") int size, @RequestParam("keyword") String keyword,
			@RequestParam("sortType") String sortType) {

		try {
			List<Courier> courier = new ArrayList<>();
			Pageable pagingSort = PageRequest.of(currentPage, size);

			System.out.println("currentPage : " + currentPage);
			System.out.println("size : " + size);
			System.out.println("keyword : " + keyword);
			System.out.println("sortType : " + sortType);

			Page<Courier> pages;

			if (sortType.equals("ASC")) {
				pages = this.courierRepository.findByIsDeleteASC(keyword, false, pagingSort, sortType);
			} else {
				pages = this.courierRepository.findByIsDeleteDESC(keyword, false, pagingSort, sortType);
			}

			courier = pages.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("pages", pages.getNumber());
			response.put("total", pages.getTotalElements());
			response.put("total_pages", pages.getTotalPages());
			response.put("datakurir", courier); // list ada disini

			System.out.println("cek pages " + pages.getNumber());
			System.out.println("cek total " + pages.getTotalElements());
			System.out.println("cek total_pages " + pages.getTotalPages());
			System.out.println("cek kurir " + courier);
			System.out.println("cek number_element " + pages.getNumberOfElements());
			System.out.println("cek pagingSort " + pagingSort);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("courier/add") // Post = menambahkan data baru ke server //Request Body = form data
	public ResponseEntity<Object> saveCourier(@RequestBody Courier courier) {
		courier.createdBy = (long) 1;
		courier.createdOn = LocalDateTime.now();
		courier.isDelete = false;

		Courier courierData = this.courierRepository.save(courier);

		if (courierData.equals(courier)) {
			return new ResponseEntity<>("Save Data Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Save Failed", HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("courier/{id}") // Pathvariable = melempar id
	public ResponseEntity<Object> getCourierById(@PathVariable("id") Long id) {
		try {
			Optional<Courier> courier = this.courierRepository.findById(id);
			return new ResponseEntity<>(courier, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("courier/edit") // Post = menambahkan data baru ke server //Request Body = form data
	public Courier edit(@RequestBody Courier courier) {
//		courier.createdBy = (long) 1;
//		courier.createdOn = LocalDateTime.now();
//		courier.isDelete = false;

		Courier courierData = this.courierRepository.save(courier);
		
		return courierData;

//		if (courierData.) {
//			return new ResponseEntity<>("Save Data Success", HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>("Save Failed", HttpStatus.BAD_REQUEST);
//
//		}

	}
	
	@PutMapping("courier/edit/{id}")
	public ResponseEntity<Object> editCourier(@PathVariable("id") Long id, @RequestBody Courier courier) {
		// Mengambil data kurir dari repositori berdasarkan ID yang diberikan
		Optional<Courier> courierData = this.courierRepository.findById(id);

		// Memeriksa apakah data kurir ditemukan berdasarkan ID
		if (courierData.isPresent()) {
			// Jika data kurir ditemukan, update propertinya
			courier.id = id; // Setel ID kurir sesuai dengan ID yang diberikan
			courier.modifiedBy = (long) 1; // Setel pengubah menjadi 1 (contoh nilai, bisa disesuaikan)
			courier.modifiedOn = LocalDateTime.now(); // Setel waktu modifikasi dengan waktu saat ini
			courier.isDelete = false; // Setel isDelete ke false karena data sedang diubah, bukan dihapus
			courier.createdBy = courierData.get().createdBy; // Gunakan nilai createdBy yang sudah ada
			courier.createdOn = courierData.get().createdOn; // Gunakan nilai createdOn yang sudah ada

			// Menyimpan data kurir yang telah diperbarui ke repositori
			this.courierRepository.save(courier);

			// Mengembalikan respons sukses dengan status OK
			return new ResponseEntity<>("Updated Data Success", HttpStatus.OK);
		} else {
			// Jika data kurir tidak ditemukan berdasarkan ID, kembalikan respons not found
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("courier/delete/{id}")
	public ResponseEntity<Object> deleteCourier(@PathVariable("id") Long id) {
		Optional<Courier> courierData = this.courierRepository.findById(id);

		if (courierData.isPresent()) {
			Courier courier = new Courier();
			courier.id = id;
			courier.name = courierData.get().name;
			courier.createdBy = courierData.get().createdBy;
			courier.createdOn = courierData.get().createdOn;
			courier.modifiedBy = courierData.get().modifiedBy;
			courier.modifiedOn = courierData.get().modifiedOn;
			courier.deletedBy = (long) 1;
			courier.deletedOn = LocalDateTime.now();
			courier.isDelete = true;

			this.courierRepository.save(courier);
			return new ResponseEntity<>("Deleted Data Success", HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("checkname")
	public ResponseEntity<Object> checknameisexist(@RequestParam("name") String name, @RequestParam("id") Long id) {
		Boolean isExist = false;
		Courier courier = null;
		if (id == 0) {
			courier = this.courierRepository.findByIdName(name);
		} else {
			courier = this.courierRepository.findByIdNameForEdit(name, id);
		}

		if (courier != null) {
			isExist = true;
		}

		return new ResponseEntity<>(isExist, HttpStatus.OK);
	}

	@GetMapping("checkname/edit")
	public ResponseEntity<Object> checknameisexisEdit(@RequestParam("name") String name) {
		Boolean isExist = false;
		Courier courier = null;

		courier = this.courierRepository.findCourierByName(name);

		if (courier != null) {
			isExist = true;
		}

		return new ResponseEntity<>(isExist, HttpStatus.OK);
	}

}
