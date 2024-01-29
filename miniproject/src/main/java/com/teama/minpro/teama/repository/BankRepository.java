package com.teama.minpro.teama.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teama.minpro.teama.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {
	@Query(value = "select b from Bank b where b.isDelete = false")
	List<Bank> findByIsDelete();
	
	//PAGING
	@Query(value = "select * from m_bank where lower(name) like lower (concat('%',?1,'%')) and va_code like(concat('%',?1,'%')) and is_delete = ?2 order by name asc", nativeQuery = true)
	Page<Bank> dataASC(String keyword, Boolean isDelete, Pageable page, String sortType);
	
	@Query(value = "select * from m_bank where lower(name) like lower (concat('%',?1,'%')) and va_code like(concat('%',?1,'%')) and is_delete = ?2 order by name desc", nativeQuery = true)
	Page<Bank> dataDESC(String keyword, Boolean isDelete, Pageable page, String sortType);
	
	//GET ID
	@Query(value = "select * from m_bank where id = ?1", nativeQuery = true)
	Optional<Bank> findByIdd(Long id);
	
	 

}
