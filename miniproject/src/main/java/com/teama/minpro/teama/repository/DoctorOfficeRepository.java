package com.teama.minpro.teama.repository;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.teama.minpro.teama.model.DoctorOffice;

public interface DoctorOfficeRepository extends JpaRepository<DoctorOffice, Long> {
	
	@Query(value = "select * from t_doctor_office where doctor_id = ?1", nativeQuery = true)
	List<DoctorOffice> listDoctorOffice(Long id);	
	
	@Query(value = "SELECT extract(year from min(tdo.start_date)) AS tahun_awal, extract(year from now()) AS tahun_akhir\r\n"
			+ "FROM t_doctor_office tdo\r\n"
			+ "WHERE tdo.doctor_id = ?1" ,nativeQuery = true)
	Map<String, Object> listpengalaman(Long id);

	@Query(value = "select tdo.doctor_id,mb.fullname,ms.name,mb.image_path,extract (year from now()) - extract(year from min(tdo.start_date)) as pengalaman\r\n"
			+ "from t_doctor_office tdo \r\n"
			+ "join m_medical_facility mmf on tdo.medical_facility_id = mmf.id\r\n"
			+ "join m_location ml on mmf.location_id = ml.id\r\n"
			+ "join m_location_level mll on ml.location_level_id = mll.id\r\n"
			+ "join m_doctor md on tdo.doctor_id = md.id \r\n"
			+ "join t_doctor_treatment tdt on md.id = tdt.id\r\n"
			+ "join m_biodata mb on md.biodata_id = mb.id\r\n"
			+ "join t_current_doctor_specialization tcds on md.id = tcds.doctor_id\r\n"
			+ "join m_specialization ms on tcds.specialization_id = ms.id\r\n"
			+ "where lower(ms.name) like lower(concat('%',?1,'%')) and lower(mll.name) like lower(concat('%',?2,'%')) \r\n"
			+ "						and lower(tdt.name) like lower(concat('%',?3,'%')) \r\n"
			+ "						and lower(mb.fullname) like lower(concat('%',?4,'%'))  and tdo.end_date is null\r\n"
			+ "						group by tdo.doctor_id,mb.fullname,ms.name,mb.image_path", nativeQuery = true)
	List<Map<String, Object>> searchDoctorPage(String spec, String loc, String treat, String name, Pageable page);

	@Query(value = "select tdo.doctor_id,mb.fullname,ms.name,mb.image_path,extract (year from now()) - extract(year from min(tdo.start_date)) as pengalaman\r\n"
			+ "			from t_doctor_office tdo \r\n"
			+ "			join m_medical_facility mmf on tdo.medical_facility_id = mmf.id\r\n"
			+ "			join m_location ml on mmf.location_id = ml.id\r\n"
			+ "			join m_location_level mll on ml.location_level_id = mll.id\r\n"
			+ "			join m_doctor md on tdo.doctor_id = md.id \r\n"
			+ "			join t_doctor_treatment tdt on md.id = tdt.id\r\n"
			+ "			join m_biodata mb on md.biodata_id = mb.id\r\n"
			+ "			join t_current_doctor_specialization tcds on md.id = tcds.doctor_id\r\n"
			+ "			join m_specialization ms on tcds.specialization_id = ms.id\r\n"
			+ "			where lower(ms.name) like lower(concat('%',?1,'%')) and lower(mll.name) like lower(concat('%',?2,'%')) \r\n"
			+ "									and lower(tdt.name) like lower(concat('%',?3,'%')) \r\n"
			+ "									and lower(mb.fullname) like lower(concat('%',?4,'%'))  and tdo.end_date is null\r\n"
			+ "									group by tdo.doctor_id,mb.fullname,ms.name,mb.image_path", nativeQuery = true)
	List<Map<String, Object>> searchDoctor(String spec, String loc, String treat, String name);

}
