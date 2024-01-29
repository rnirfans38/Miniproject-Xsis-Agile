package com.teama.minpro.teama.repository;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teama.minpro.teama.model.MedicalFacilityCategory;

public interface MedicalRepository extends JpaRepository<MedicalFacilityCategory, Long> {
	@Query(value = "SELECT m FROM MedicalFacilityCategory m WHERE m.isDelete = false") // pakai java class harus pakai alias
	List<MedicalFacilityCategory> findByIsDelete();

	// PAGING //
	@Query(value = "select * from m_medical_facility_category where lower(name) like lower(concat('%',?1,'%')) and is_delete = ?2 order by name asc", nativeQuery = true)
	Page<MedicalFacilityCategory> findByIsDeleteASC(String keyword, Boolean isDelete, Pageable page);

	@Query(value = "select * from m_medical_facility_category where lower(name) like lower(concat('%',?1,'%')) and is_delete = ?2 order by name  desc ", nativeQuery = true)
	Page<MedicalFacilityCategory> findByIsDeleteDESC(String keyword, Boolean isDelete, Pageable page);
	
	@Query(value="SELECT * FROM m_medical_facility_category WHERE is_delete = false AND lower(trim(name)) = lower(trim(?1))", nativeQuery=true)
	MedicalFacilityCategory findByIdName(String name);
	
	@Query(value="SELECT * FROM m_medical_facility_category WHERE is_delete = false AND lower(trim(name)) = lower(trim(?1)) AND id != ?2", nativeQuery=true)
	MedicalFacilityCategory findByIdNameForEdit(String name, Long id);
	
	@Query(value="select mb.fullname,mmfc.name from m_medical_facility_category mmfc \r\n"
			+ "join m_user mu on mmfc.modified_by = mu.id\r\n"
			+ "join m_biodata mb on mu.biodata_id = mb.id",  nativeQuery=true)
	List<Map<String, Object>> diubahOleh(Long id);
	

}
