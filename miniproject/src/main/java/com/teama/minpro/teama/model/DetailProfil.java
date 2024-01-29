package com.teama.minpro.teama.model;


public interface DetailProfil {

//select b.fullname, c.specialization, extract(year from now()) - extract(year from min(c.start_date)) as pengalaman

	String getFullname();

	String getSpecialization();

	Integer getPengalaman();
}
