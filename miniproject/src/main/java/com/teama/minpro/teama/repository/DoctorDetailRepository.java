package com.teama.minpro.teama.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teama.minpro.teama.model.Doctor;

public interface DoctorDetailRepository extends JpaRepository<Doctor, Long> {
	@Query(value = "SELECT de.institution_name, de.end_year, de.major "
			+ "FROM m_doctor d JOIN m_doctor_education de on d.id=de.doctor_id WHERE d.id = ?1", nativeQuery = true)
	List<Map<String, Object>> pendidikan(Long id);

	@Query(value =  "SELECT\r\n"
			+ "extract(year from tdo.start_date) AS tahun_awal,  \r\n"
			+ "extract(year from tdo.end_date) AS tahun_akhir,  \r\n"
			+ "mdf.name, \r\n"
			+ "tdo.specialization, \r\n"
			+ "mll.name AS lokasi, \r\n"
			+ "tdo.is_delete  \r\n"
			+ "FROM t_doctor_office tdo  \r\n"
			+ "JOIN m_medical_facility mdf on tdo.medical_facility_id = mdf.id  \r\n"
			+ "JOIN m_location ml on mdf.location_id = ml.id  \r\n"
			+ "JOIN m_location_level mll on ml.location_level_id = mll.id  \r\n"
			+ "WHERE tdo.doctor_id = ?1 AND mdf.id<>5 \r\n"
			+ "ORDER BY tahun_awal DESC",nativeQuery = true)
	List<Map<String, Object>> listRiwayat(Long doctor_id);

	@Query(value = "   SELECT tdo.specialization, b.fullname, b.image_path , tdotp.price\r\n"
			+ "					 FROM m_doctor d\r\n"
			+ "						JOIN m_biodata b ON d.biodata_id = b.id\r\n"
			+ "						JOIN t_doctor_office tdo ON d.id = tdo.doctor_id\r\n"
			+ "						JOIN m_medical_facility mmf ON tdo.medical_facility_id = mmf.id\r\n"
			+ "						JOIN t_doctor_office_treatment tdot ON tdo.id = tdot.doctor_office_id\r\n"
			+ "						JOIN t_doctor_office_treatment_price tdotp ON tdot.id =tdotp.doctor_office_treatment_id\r\n"
			+ "						WHERE b.id = ?1 and mmf.id = 5\r\n"
			+ "						GROUP BY tdo.specialization, b.fullname, b.image_path, tdotp.price", nativeQuery = true)
	Map<String, Object> nama(Long id);

	@Query(value = "SELECT dt.name\r\n"
			+ "FROM m_doctor d \r\n"
			+ "JOIN t_doctor_treatment dt on d.id = dt.doctor_id\r\n"
			+ "WHERE d.id = ?1",nativeQuery= true)
	List<Map<String, Object>> tindakan(Long id);


	@Query(value = "SELECT	mll.name AS kab,\r\n"
			+ "					ml.name AS kec,\r\n"
			+ "					tdotp.price_start_from,\r\n"
			+ "					tdo.specialization, \r\n"
			+ "					mf.name, \r\n"
			+ "					mf.full_address,\r\n"
			+ "					mf.id,\r\n"
			+ "					mfs.day,\r\n"
			+ "					mfs.time_schedule_start,\r\n"
			+ "					mfs.time_schedule_end \r\n"
			+ "					from m_doctor md \r\n"
			+ "					join t_doctor_office_schedule tdos on tdos.doctor_id = md.id \r\n"
			+ "					join m_medical_facility_schedule mfs on mfs.id = tdos.medical_facility_schedule_id\r\n"
			+ "					join m_medical_facility mf on mfs.medical_facility_id = mf.id\r\n"
			+ "					join m_location ml on mf.location_id = ml.id\r\n"
			+ "					join m_location_level mll on ml.location_level_id = mll.id\r\n"
			+ "					join t_doctor_office tdo on mf.id = tdo.medical_facility_id AND tdo.doctor_id = md.id\r\n"
			+ "					join t_doctor_office_treatment tdot on tdo.id = tdot.doctor_office_id\r\n"
			+ "					join t_doctor_office_treatment_price tdotp on tdotp.doctor_office_treatment_id = tdot.id\r\n"
			+ "					where md.id = ?1 \r\n"
			+ "					and mf.id <> 5 \r\n"
			+ "					and tdo.end_date is null\r\n"
			+ "					GROUP BY \r\n"
			+ "					mll.name,\r\n"
			+ "					ml.name,\r\n"
			+ "					tdotp.price_start_from,\r\n"
			+ "					tdo.specialization, \r\n"
			+ "					mf.name, \r\n"
			+ "					mf.full_address,\r\n"
			+ "					mf.id,\r\n"
			+ "					mfs.day,\r\n"
			+ "					mfs.time_schedule_start,\r\n"
			+ "					mfs.time_schedule_end\r\n"
			+ "					order by mf.id asc, mfs.day desc", nativeQuery = true)
	List<Map<String, Object>> listDetail(Long id);

	
	@Query(value = "select distinct * from ( \r\n"
			+ "	SELECT *,\r\n"
			+ "			CASE\r\n"
			+ "				WHEN\r\n"
			+ "				(SELECT count(1) \r\n"
			+ "					FROM m_medical_facility_schedule mmfsh \r\n"
			+ "				 	JOIN t_doctor_office_schedule tdos ON tdos.medical_facility_schedule_id = mmfsh.id \r\n"
			+ "			 		WHERE tdos.doctor_id = jadwal.doctor_id AND mmfsh.medical_facility_id = 5 \r\n"
			+ "				    AND mmfsh.day=current_day_indonesia \r\n"
			+ "			 		AND TO_TIMESTAMP(to_char(now(),'HH24:MI'),'HH24:MI') \r\n"
			+ "				  	BETWEEN TO_TIMESTAMP(mmfsh.time_schedule_start,'HH24:MI') \r\n"
			+ "				  	and TO_TIMESTAMP(mmfsh.time_schedule_end,'HH24:MI'))>0 then true else false end AS is_online		 \r\n"
			+ "				FROM\r\n"
			+ "			(SELECT \r\n"
			+ "			tdos.doctor_id,\r\n"
			+ "			mll.name AS kab,   \r\n"
			+ "			mfs.day,  \r\n"
			+ "			mfs.time_schedule_start,  \r\n"
			+ "			mfs.time_schedule_end,\r\n"
			+ "				CASE\r\n"
			+ "				WHEN EXTRACT(dow FROM now()) = 1 THEN 'Senin' \r\n"
			+ "				WHEN EXTRACT(dow FROM now()) = 2 THEN 'Selasa'\r\n"
			+ "				WHEN EXTRACT(dow FROM now()) = 3 THEN 'Rabu'\r\n"
			+ "				WHEN EXTRACT(dow FROM now()) = 4 THEN 'Kamis'\r\n"
			+ "				WHEN EXTRACT(dow FROM now()) = 5 THEN 'Jumat'\r\n"
			+ "				WHEN EXTRACT(dow FROM now()) = 6 THEN 'Sabtu'\r\n"
			+ "			 	WHEN EXTRACT(dow FROM now()) = 0 THEN 'Minggu'\r\n"
			+ "				END AS current_day_indonesia\r\n"
			+ "			 from m_doctor md \r\n"
			+ "								join t_doctor_office_schedule tdos on tdos.doctor_id = md.id \r\n"
			+ "								join m_medical_facility_schedule mfs on mfs.id = tdos.medical_facility_schedule_id\r\n"
			+ "								join m_medical_facility mf on mfs.medical_facility_id = mf.id\r\n"
			+ "								join m_location ml on mf.location_id = ml.id\r\n"
			+ "								join m_location_level mll on ml.location_level_id = mll.id\r\n"
			+ "								join t_doctor_office tdo on mf.id = tdo.medical_facility_id AND tdo.doctor_id = md.id\r\n"
			+ "								join t_doctor_office_treatment tdot on tdo.id = tdot.doctor_office_id\r\n"
			+ "								join t_doctor_office_treatment_price tdotp on tdotp.doctor_office_treatment_id = tdot.id\r\n"
			+ "					  WHERE tdo.is_delete = false AND md.id = 3 and mf.id=5)AS jadwal\r\n"
			+ ") Data_Online	",nativeQuery= true)
	List<Map<String, Object>> listDetailOnline(Long id);
}

