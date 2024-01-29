package com.teama.minpro.teama.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teama.minpro.teama.model.MenuMakanan;
import com.teama.minpro.teama.repository.MenuMakananRepo;

@RestController
@RequestMapping("/api/")
public class ApiMenuMakanan {

	@Autowired
	private MenuMakananRepo MenuMakananRepo;

	@GetMapping("menu")
	public ResponseEntity<List<MenuMakanan>> getAllMenu() {
		try {
			List<MenuMakanan> dataMenu = this.MenuMakananRepo.getAllMenu();
			return new ResponseEntity<>(dataMenu, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping("hargamenu/{id}")
	public ResponseEntity<Object> getAllMenuHarga(@PathVariable("id") Long id) {
		try {
			Optional<Object> hargaMenu = this.MenuMakananRepo.getNamaHarga(id);
			if (hargaMenu.isPresent()) {
				return new ResponseEntity<>(hargaMenu, HttpStatus.OK);
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
}
