package com.teama.minpro.teama.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teama.minpro.teama.model.CustomerRelation;

public interface CustomerRelationRepository extends JpaRepository<CustomerRelation, Long>{
	
	@Query(value="SELECT * FROM m_customer_relation WHERE LOWER(name) LIKE LOWER(CONCAT('%',?1,'%')) AND is_delete = false ORDER BY NAME ASC",nativeQuery=true)
	List<CustomerRelation> findCustomerRelation(String keyword);

	@Query(value="SELECT * FROM m_customer_relation WHERE LOWER(name) LIKE LOWER(CONCAT('%',?1,'%')) AND is_delete =?2 ORDER BY name ASC", nativeQuery=true)
	Page<CustomerRelation> findASC(String keyword, Boolean isDelete, Pageable page);
	
	@Query(value="SELECT * FROM m_customer_relation WHERE LOWER(name) LIKE LOWER(CONCAT('%',?1,'%')) AND is_delete =?2 ORDER BY name DESC", nativeQuery=true)
	Page<CustomerRelation> findDESC(String keyword, Boolean isDelete, Pageable page);
	
	@Query(value="SELECT * FROM m_customer_relation WHERE is_delete = false AND name =?1", nativeQuery=true)
	CustomerRelation findByIdName(String name);
	
	@Query(value="SELECT * FROM m_customer_relation WHERE is_delete = false AND name =?1 AND id != ?2 LIMIT 1", nativeQuery=true)
	CustomerRelation findByIdNameForEdit(String name, Long id);
}
