package com.teama.minpro.teama.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teama.minpro.teama.repository.DoctorDetailRepository;
import com.teama.minpro.teama.repository.DoctorOfficeRepository;



@Service
@Transactional
public class DetailDokterService {

	@Autowired
	private DoctorDetailRepository dokde;
	
	public List<Map<String, Object>> listdetailonline(Long id) {
		return dokde.listDetailOnline(id);
	}
	
	public List<Map<String, Object>> listdetail(Long id) {
		return dokde.listDetail(id);
	}
	
//	public List<Map<String, Object>> waktudetail(long idRS) {
//		return dokde.waktudetail(idRS);
//	}
	
	public List<Map<String, Object>> tindakan(Long id) {
		return dokde.tindakan(id);
	}
	
	public List<Map<String, Object>> pendidikan(Long id) {
		return dokde.pendidikan(id);
	}
	
	public List<Map<String, Object>> riwayat(Long doctor_id) {
		return dokde.listRiwayat(doctor_id);
	}
	
	public Map<String, Object> nama(Long id) {
		return dokde.nama(id);
	}
	
	@Autowired DoctorOfficeRepository dor;
	public Map<String, Object> pengalaman(Long id) {
		return dor.listpengalaman(id);
	}

}
