package com.teama.minpro.teama.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teama.minpro.teama.model.LocationLevel;

public interface LocationLevelRepository extends JpaRepository<LocationLevel, Long> {

	@Query(value = "select id, name from m_location_level",nativeQuery = true)
	List <Map<String, Object>> modalLokasi();


}
