package com.teama.minpro.teama.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_doctor_office_treatment")
public class DoctorOfficeTreatment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long id;
	
	@Column(name="doctor_treatment_id")
	public Long doctorTreatmentId;
	
	@Column(name="doctor_office_id")
	public Long doctorOfficeId;
	
	@Column(name = "created_by")
	public Long createdBy;

	@Column(name = "created_on")
	public LocalDateTime createdOn;

	@Column(name = "modified_by")
	public Long modifiedBy;

	@Column(name = "modified_on")
	public LocalDateTime modifiedOn;

	@Column(name = "deleted_by")
	public Long deletedBy;

	@Column(name = "deleted_on")
	public LocalDateTime deletedOn;

	@Column(name = "is_delete")
	public Boolean isDelete;
	
	@ManyToOne
	@JoinColumn(name="doctor_treatment_id",updatable=false,insertable=false)
	public DoctorTreatment doctorTreatment;
	
	@ManyToOne
	@JoinColumn(name="doctor_office_id",updatable=false,insertable=false)
	public DoctorOffice doctorOffice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDoctorTreatmentId() {
		return doctorTreatmentId;
	}

	public void setDoctorTreatmentId(Long doctorTreatmentId) {
		this.doctorTreatmentId = doctorTreatmentId;
	}

	public Long getDoctorOfficeId() {
		return doctorOfficeId;
	}

	public void setDoctorOfficeId(Long doctorOfficeId) {
		this.doctorOfficeId = doctorOfficeId;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Long getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(Long deletedBy) {
		this.deletedBy = deletedBy;
	}

	public LocalDateTime getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(LocalDateTime deletedOn) {
		this.deletedOn = deletedOn;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public DoctorTreatment getDoctorTreatment() {
		return doctorTreatment;
	}

	public void setDoctorTreatment(DoctorTreatment doctorTreatment) {
		this.doctorTreatment = doctorTreatment;
	}

	public DoctorOffice getDoctorOffice() {
		return doctorOffice;
	}

	public void setDoctorOffice(DoctorOffice doctorOffice) {
		this.doctorOffice = doctorOffice;
	}

	
}
