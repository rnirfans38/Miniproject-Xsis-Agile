package com.teama.minpro.teama.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teama.minpro.teama.model.DoctorTreatment;

public interface DoctorTreatmentRepository extends JpaRepository <DoctorTreatment, Long>{
	@Query(value = "select id, name from t_doctor_treatment",nativeQuery = true)
	
List <Map<String, Object>> modalTindakan();
 	

}
