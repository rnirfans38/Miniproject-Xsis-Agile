package com.teama.minpro.teama.repository;


import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teama.minpro.teama.model.Courier;




public interface CourierRepository extends JpaRepository<Courier, Long> {
	@Query(value = "SELECT c FROM Courier c WHERE c.isDelete = false") // pakai java class harus pakai alias
	List<Courier> findByIsDelete();
	
	// PAGING // 
	@Query(value = "select * from m_courier where lower(name) like lower(concat('%',?1,'%')) and is_delete = ?2 order by name asc ", nativeQuery = true)
	Page<Courier> findByIsDeleteASC(String keyword, Boolean isDelete, Pageable page, String sortType); /*Query mencocokan name in databes & search*/

	@Query(value = "select * from m_courier where lower(name) like lower(concat('%',?1,'%')) and is_delete = ?2 order by name  desc ", nativeQuery = true)
	Page<Courier> findByIsDeleteDESC(String keyword, Boolean isDelete, Pageable page, String sortType); /*Query mencocokan name in databes & search*/
	
	@Query(value="SELECT * FROM m_courier WHERE is_delete = false AND lower(trim(name)) = lower(trim(?1))", nativeQuery=true)
	Courier findByIdName(String name);
	
	@Query(value="SELECT * FROM m_courier WHERE is_delete = false AND lower(trim(name)) = lower(trim(?1)) AND id != ?2", nativeQuery=true)
	Courier findByIdNameForEdit(String name, Long id);
	
	@Query(value="select * from m_courier where name = ?",nativeQuery=true)
	Courier findCourierByName(String name);

}
