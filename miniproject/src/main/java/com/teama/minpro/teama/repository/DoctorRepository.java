package com.teama.minpro.teama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teama.minpro.teama.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	

}
