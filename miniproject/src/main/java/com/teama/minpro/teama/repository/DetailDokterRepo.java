package com.teama.minpro.teama.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teama.minpro.teama.model.DetailProfil;
import com.teama.minpro.teama.model.Doctor;

public interface DetailDokterRepo extends JpaRepository<Doctor, Long> {

	// GET FULLNAME, SPEZIALIZATION, PENGALAMAN (PROFIL)
	@Query(value = "select b.fullname, c.specialization, extract(year from now()) - extract(year from min(c.start_date)) as pengalaman \r\n"
			+ "from m_doctor a \r\n" + "join m_biodata b on a.biodata_id = b.id\r\n"
			+ "join t_doctor_office c on a.id = c.doctor_id\r\n" + "where c.doctor_id = ?1 \r\n"
			+ "group by b.fullname, c.specialization", nativeQuery = true)
	List<Map<String, Object>> profil(Long id);
	
	//PROFIL Pake model
	@Query(value = "select b.fullname, c.specialization, extract(year from now()) - extract(year from min(c.start_date)) as pengalaman \r\n"
			+ "from m_doctor a \r\n" + "join m_biodata b on a.biodata_id = b.id\r\n"
			+ "join t_doctor_office c on a.id = c.doctor_id\r\n" + "where c.doctor_id = ?1 \r\n"
			+ "group by b.fullname, c.specialization", nativeQuery = true)
	List<DetailProfil> detailProfil(Long id);

	
	
	
	
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////////
	// all doctor
	@Query(value = "select * from m_doctor where str = ?1", nativeQuery = true)
	List<Doctor> allDoctor(String str);

	@Query(value = "SELECT tdt.name \r\n" + "FROM m_doctor md \r\n"
			+ "JOIN t_doctor_treatment tdt ON md.id = tdt.doctor_id\r\n" + "WHERE md.id = ?1", nativeQuery = true)
	List<Map<String, Object>> findTreatment(Long id);

	// data dokter
	@Query(value = "select tdo.id, md.str , md.created_by, tdo.created_by as tdoCrt from m_doctor md join t_doctor_office tdo on md.id = tdo.doctor_id", nativeQuery = true)
	List<Map<String, Object>> findData();
}
