package com.teama.minpro.teama.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.teama.minpro.teama.model.RumahMakan;

public interface RMRepository extends JpaRepository<RumahMakan, Long> {
//1. PAGING(SORT ASC)
	@Query(value = "select * from rumah_makan where lower(pembeli) like lower(concat('%',?1,'%')) and is_delete = ?2 order by pembeli asc", nativeQuery = true)
	Page<RumahMakan> byASC(String keyword, Boolean isDelete, Pageable page, String sortType); /*Query mencocokan name in databes & search*/
//2. PAGING(SORT DESC)
	@Query(value = "select * from rumah_makan where lower(pembeli) like lower(concat('%',?1,'%')) and is_delete = ?2 order by pembeli desc", nativeQuery = true)
	Page<RumahMakan> byDESC(String keyword, Boolean isDelete, Pageable page, String sortType); /*Query mencocokan name in databes & search*/
	
//Testing validation menu with id
@Query(value = "SELECT * FROM rumah_makan Where is_delete = false AND menu_id = ?1", nativeQuery=true)
RumahMakan validationId(Long menuId);
}
