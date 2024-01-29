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
import com.teama.minpro.teama.model.RumahMakan;
import com.teama.minpro.teama.repository.RMRepository;

@RestController
@RequestMapping("/api/")
public class ApiRMController {

	@Autowired
	private RMRepository RMRepo;

	// UNTUK PAGINATION
	@GetMapping("rm/paging") // Get = menampilkan
	public ResponseEntity<Map<String, Object>> getRMPages(@RequestParam(defaultValue = "0") int currentPage,
			@RequestParam(defaultValue = "5") int size, @RequestParam("keyword") String keyword,
			@RequestParam("sortType") String sortType) {

		try {
			List<RumahMakan> RM = new ArrayList<>();
			Pageable pagingSort = PageRequest.of(currentPage, size);

			System.out.println("currentPage : " + currentPage);
			System.out.println("size : " + size);
			System.out.println("keyword : " + keyword);
			System.out.println("sortType : " + sortType);

			Page<RumahMakan> pages;

			if (sortType.equals("ASC")) {
				pages = this.RMRepo.byASC(keyword, false, pagingSort, sortType);
			} else {
				pages = this.RMRepo.byDESC(keyword, false, pagingSort, sortType);
			}

			RM = pages.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("pages", pages.getNumber());
			response.put("total", pages.getTotalElements());
			response.put("total_pages", pages.getTotalPages());
			response.put("dataRM", RM); // list data (response) ada disini

			System.out.println("cek pages " + pages.getNumber());
			System.out.println("cek total " + pages.getTotalElements());
			System.out.println("cek total_pages " + pages.getTotalPages());
			System.out.println("cek rm " + RM);
			System.out.println("cek number_element " + pages.getNumberOfElements());
			System.out.println("cek pagingSort " + pagingSort);
			System.out.print(response);
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("rm/add") // Post = menambahkan data baru ke server //Request Body = form data
	public ResponseEntity<Object> saveRM(@RequestBody RumahMakan RM) {
		RM.createdBy = "RM Mitoha";
		RM.createdOn = LocalDateTime.now();
		RM.isDelete = false;

		RumahMakan rumahMakan = this.RMRepo.save(RM);

		if (rumahMakan.equals(RM)) {
			return new ResponseEntity<>("Save Data Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Save Failed", HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("rm/{id}") // Pathvariable = melempar id
	public ResponseEntity<Object> getRMId(@PathVariable("id") Long id) {
		try {
			Optional<RumahMakan> rumahmakan = this.RMRepo.findById(id);
			return new ResponseEntity<>(rumahmakan, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("rm/edit/{id}")
	public ResponseEntity<Object> editRM(@PathVariable("id") Long id, @RequestBody RumahMakan rumahmakan) {
		// Mengambil data kurir dari repositori berdasarkan ID yang diberikan
		Optional<RumahMakan> rmData = this.RMRepo.findById(id);

		// Memeriksa apakah data kurir ditemukan berdasarkan ID
		if (rmData.isPresent()) {
			// Jika data kurir ditemukan, update propertinya
			rumahmakan.id = id; // Setel ID kurir sesuai dengan ID yang diberikan
			rumahmakan.modifiedBy = "RM Mitoha"; // Setel pengubah menjadi 1 (contoh nilai, bisa disesuaikan)
			rumahmakan.modifiedOn = LocalDateTime.now(); // Setel waktu modifikasi dengan waktu saat ini
			rumahmakan.isDelete = false; // Setel isDelete ke false karena data sedang diubah, bukan dihapus
			rumahmakan.createdBy = rmData.get().createdBy; // Gunakan nilai createdBy yang sudah ada
			rumahmakan.createdOn = rmData.get().createdOn; // Gunakan nilai createdOn yang sudah ada

			// Menyimpan data kurir yang telah diperbarui ke repositori
			this.RMRepo.save(rumahmakan);

			// Mengembalikan respons sukses dengan status OK
			return new ResponseEntity<>("Updated Data Success", HttpStatus.OK);
		} else {
			// Jika data kurir tidak ditemukan berdasarkan ID, kembalikan respons not found
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("rm/delete/{id}")
	public ResponseEntity<Object> deleteRM(@PathVariable("id") Long id) {
		Optional<RumahMakan> rmData = this.RMRepo.findById(id);

		if (rmData.isPresent()) {
			RumahMakan rumahmakan = new RumahMakan();
			rumahmakan.id = id;
			// rumahmakan.menu = rmData.get().menu;
			rumahmakan.pembeli = rmData.get().pembeli;
			// rumahmakan.harga = rmData.get().harga;
			rumahmakan.createdBy = rmData.get().createdBy;
			rumahmakan.createdOn = rmData.get().createdOn;
			rumahmakan.modifiedBy = rmData.get().modifiedBy;
			rumahmakan.modifiedOn = rmData.get().modifiedOn;
			rumahmakan.deletedBy = "RM Mitoha";
			rumahmakan.deletedOn = LocalDateTime.now();
			rumahmakan.isDelete = true;
			rumahmakan.menuId = rmData.get().menuId;     

			this.RMRepo.save(rumahmakan);
			return new ResponseEntity<>("Deleted Data Success", HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	@GetMapping("checkid")
	public ResponseEntity<Object> checkid(@RequestParam("menuId") Long id) {
		Boolean existId = false;
		RumahMakan rumahmakan = null;
		
			rumahmakan = this.RMRepo.validationId(id);
		

		if (rumahmakan != null) {
			existId = true;
		}

		return new ResponseEntity<>(existId, HttpStatus.OK);
	}


}
