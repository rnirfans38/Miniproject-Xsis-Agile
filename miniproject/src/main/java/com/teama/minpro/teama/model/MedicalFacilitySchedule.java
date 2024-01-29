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
@Table(name = "m_medical_facility_schedule")
public class MedicalFacilitySchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long id;

	@Column(name = "medical_facility_id")
	public Long medicalFacilityId;

	@Column(name = "day")
	public String day;

	@Column(name = "time_schedule_start")
	public String timeScheduleStart;

	@Column(name = "time_schedule_end")
	public String timeScheduleEnd;

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
	@JoinColumn(name = "medical_facility_id", updatable = false, insertable = false)
	public MedicalFacility medicalFacility;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMedicalFacilityId() {
		return medicalFacilityId;
	}

	public void setMedicalFacilityId(Long medicalFacilityId) {
		this.medicalFacilityId = medicalFacilityId;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTimeScheduleStart() {
		return timeScheduleStart;
	}

	public void setTimeScheduleStart(String timeScheduleStart) {
		this.timeScheduleStart = timeScheduleStart;
	}

	public String getTimeScheduleEnd() {
		return timeScheduleEnd;
	}

	public void setTimeScheduleEnd(String timeScheduleEnd) {
		this.timeScheduleEnd = timeScheduleEnd;
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

	public MedicalFacility getMedicalFacility() {
		return medicalFacility;
	}

	public void setMedicalFacility(MedicalFacility medicalFacility) {
		this.medicalFacility = medicalFacility;
	}


}