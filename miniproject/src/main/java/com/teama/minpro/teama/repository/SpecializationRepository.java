package com.teama.minpro.teama.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teama.minpro.teama.model.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
	@Query(value = "select id, name from m_specialization ms"
			+ " WHERE ms.is_delete = false",nativeQuery = true)
	List <Map<String, Object>> modalSpesialisasi();


}
