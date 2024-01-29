package com.teama.minpro.teama.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teama.minpro.teama.repository.DoctorTreatmentRepository;
import com.teama.minpro.teama.repository.LocationLevelRepository;
import com.teama.minpro.teama.repository.SpecializationRepository;

@Service
@Transactional
public class CariDokterService {
	
	@Autowired
	private LocationLevelRepository LLR;
	
	public List<Map<String, Object>> modalLokasi(){
		return LLR.modalLokasi();
	}
	
	@Autowired
	private SpecializationRepository SR;
	
	public List<Map<String, Object>> modalSpesialisasi() {
		return SR.modalSpesialisasi();
	}
	
	@Autowired
	private DoctorTreatmentRepository DR;
	
	public List<Map<String, Object>> modalTindakan(){
		return DR.modalTindakan();
	}

}
