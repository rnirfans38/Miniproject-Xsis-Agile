package com.teama.minpro.teama.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teama.minpro.teama.model.DoctorOfficeSchedule;

public interface DoctorOfficeScheduleRepository extends JpaRepository<DoctorOfficeSchedule, Long> {
	// tampil list schdule by medfac
	@Query(value = "SELECT tdos.doctor_id, mmf.name AS nama_rs, mll.name AS location_level,\r\n"
			+ "			ml.name AS location 	\r\n" + "			FROM t_doctor_office_schedule tdos\r\n"
			+ "				JOIN m_medical_facility_schedule mmfs on tdos.medical_facility_schedule_id = mmfs.id\r\n"
			+ "				JOIN m_medical_facility mmf on mmfs.medical_facility_id = mmf.id\r\n"
			+ "				JOIN m_location ml on mmf.location_id = ml.id\r\n"
			+ "				JOIN m_location_level mll on ml.location_level_id = mll.id\r\n"
			+ "				WHERE tdos.doctor_id = ?1 and tdos.is_delete = false and mmf.id<>5 \r\n"
			+ "				GROUP BY tdos.doctor_id, mmf.name, mll.name,\r\n" + "			ml.name  \r\n"
			+ "			order by tdos.doctor_id", nativeQuery = true)
	List<Map<String, Object>> allDoctorSchedule(Long id);

	// caridokter
	@Query(value = "SELECT *, CASE 	WHEN 	(SELECT count(1) \r\n"
			+ "					FROM m_medical_facility_schedule mmfsh \r\n"
			+ "				 	JOIN t_doctor_office_schedule tdos ON tdos.medical_facility_schedule_id = mmfsh.id \r\n"
			+ "					AND tdos.doctor_id = jadwal.doctor_id \r\n"
			+ "					AND mmfsh.medical_facility_id = 5 \r\n"
			+ "					AND mmfsh.day=JADWAL.current_day_indonesia \r\n"
			+ "			 		AND (TO_TIMESTAMP(to_char(now(),'HH24:MI'),'HH24:MI') \r\n"
			+ "				  	BETWEEN TO_TIMESTAMP(mmfsh.time_schedule_start,'HH24:MI') \r\n"
			+ "				  	and TO_TIMESTAMP(mmfsh.time_schedule_end,'HH24:MI')))>0 then true else false end AS is_online		 \r\n"
			+ "				FROM ( 	SELECT   		tdos.doctor_id,   		mb.fullname,  \r\n"
			+ "					mb.image_path,   		ms.name AS spesialis,  \r\n"
			+ "					extract(year from now()) - (SELECT EXTRACT (YEAR FROM MIN(start_date)) FROM t_doctor_office WHERE doctor_id=tdos.doctor_id) as tahun_pengalaman,  \r\n"
			+ "					mmfs.day AS day_schedule, 		mmfs.time_schedule_start AS time_mulai,  \r\n"
			+ "					mmfs.time_schedule_end AS time_beres,  \r\n"
			+ "					string_agg(tdt.name, ', ') AS tindakan,   		mll.name AS location_level,\r\n"
			+ "					CASE  		  	WHEN EXTRACT(dow FROM now()) = 0 THEN 'Senin' \r\n"
			+ "						WHEN EXTRACT(dow FROM now()) = 1 THEN 'Selasa'\r\n"
			+ "						WHEN EXTRACT(dow FROM now()) = 2 THEN 'Rabu'\r\n"
			+ "						WHEN EXTRACT(dow FROM now()) = 3 THEN 'Kamis'\r\n"
			+ "						WHEN EXTRACT(dow FROM now()) = 4 THEN 'Jumat'\r\n"
			+ "						WHEN EXTRACT(dow FROM now()) = 5 THEN 'Sabtu'\r\n"
			+ "						WHEN EXTRACT(dow FROM now()) = 6 THEN 'Minggu' 			ELSE '-'\r\n"
			+ "						END AS current_day_indonesia 			  	FROM t_doctor_office_schedule tdos  \r\n"
			+ "							  	JOIN m_medical_facility_schedule mmfs ON tdos.medical_facility_schedule_id = mmfs.id  \r\n"
			+ "							  	JOIN m_medical_facility mmf ON mmfs.medical_facility_id = mmf.id  \r\n"
			+ "							  	JOIN m_location ml ON mmf.location_id = ml.id  \r\n"
			+ "						  		JOIN m_doctor md ON tdos.doctor_id = md.id\r\n"
			+ "					  			JOIN t_doctor_office tdo ON tdo.doctor_id = md.id\r\n"
			+ "						  		JOIN m_biodata mb ON md.biodata_id = mb.id  \r\n"
			+ "						 	 	JOIN t_current_doctor_specialization tcd ON tdos.doctor_id = tcd.doctor_id  \r\n"
			+ "							  	JOIN m_specialization ms ON tcd.specialization_id = ms.id  \r\n"
			+ "							  	JOIN m_location_level mll ON ml.location_level_id = mll.id  \r\n"
			+ "							  	JOIN t_doctor_treatment tdt ON tdt.doctor_id = md.id\r\n"
			+ " 					  AND (mll.name like %?1% AND lower(mb.fullname) like %?2% AND ms.name LIKE %?3% AND tdt.name LIKE %?4%) \r\n"
			+ "					  and tdos.is_delete = false  \r\n"
			+ "						  	GROUP BY tdos.doctor_id, mb.fullname, mb.image_path, ms.name, tdo.start_date,mmf.name,  \r\n"
			+ "						  	mmfs.day, mll.name, ml.name, mmfs.time_schedule_start, mmfs.time_schedule_end) as jadwal", nativeQuery = true)
	List<Map<String, Object>> cariDokter(String lokasi, String fullname, String spesialis, String tindakan);
}
