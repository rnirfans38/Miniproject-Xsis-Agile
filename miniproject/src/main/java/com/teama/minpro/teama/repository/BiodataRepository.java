package com.teama.minpro.teama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teama.minpro.teama.model.Biodata;

public interface BiodataRepository extends JpaRepository<Biodata, Long> {
	
}
