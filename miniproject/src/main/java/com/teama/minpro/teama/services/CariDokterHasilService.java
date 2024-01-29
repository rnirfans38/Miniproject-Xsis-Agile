package com.teama.minpro.teama.services;




import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teama.minpro.teama.repository.DoctorOfficeScheduleRepository;

@Service
@Transactional
public class CariDokterHasilService {

	@Autowired
	private DoctorOfficeScheduleRepository DOSR;
	
	public List<Map<String, Object>> allDoctorSchedule(long id) {
		return DOSR.allDoctorSchedule(id);
	}
	
	public List<Map<String, Object>> cariDokter(String lokasi, String fullname, String spesialis, String tindakan) {
		return DOSR.cariDokter(lokasi, fullname, spesialis, tindakan);
	}
	
}
