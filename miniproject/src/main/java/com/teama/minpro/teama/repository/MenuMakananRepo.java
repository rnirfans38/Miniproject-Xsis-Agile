package com.teama.minpro.teama.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teama.minpro.teama.model.MenuMakanan;

public interface MenuMakananRepo extends JpaRepository<MenuMakanan, Long> {

	// Get semua menu

	@Query(value = "SELECT * FROM menu_makanan", nativeQuery = true)
	List<MenuMakanan> getAllMenu();
	
	@Query(value = "select mm.nama_menu, mm.harga_menu\r\n"
			+ "from menu_makanan mm \r\n"
			+ "where id = ?1", nativeQuery = true)
	Optional<Object> getNamaHarga(Long id);

}
