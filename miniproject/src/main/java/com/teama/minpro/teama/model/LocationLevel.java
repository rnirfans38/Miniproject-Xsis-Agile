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
@Table(name="m_location_level")
public class LocationLevel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long Id;
	
	
	@Column(name="name", length = 50)
	private String Name;
	

	@Column(name="abbreviation", length = 50)
	private String Abbreviation;
	
	@ManyToOne
	@JoinColumn(name="created_by",insertable=false, updatable=false)
	public User User_create;
	

	@Column(name="created_by")
	private Long createdBy;
	

	@Column(name="created_on")
	private LocalDateTime createdOn;
	
	@ManyToOne
	@JoinColumn(name="modified_by",insertable=false, updatable=false)
	public User User_modified;
	

	@Column(name="modified_by")
	private Long modifiedBy;

	
	@Column(name="modified_on")
	private LocalDateTime modifiedOn;
	
	@ManyToOne
	@JoinColumn(name="deleted_by",insertable=false, updatable=false)
	public User User_deleted;

	@Column(name="deleted_by")
	private Long deletedBy;
	
	@Column(name="deleted_on")
	private LocalDateTime deletedOn;
	
	@Column(name="is_delete",columnDefinition = "boolean default false")
	private Boolean isDelete;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAbbreviation() {
		return Abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		Abbreviation = abbreviation;
	}

	public User getUser_create() {
		return User_create;
	}

	public void setUser_create(User user_create) {
		User_create = user_create;
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

	public User getUser_modified() {
		return User_modified;
	}

	public void setUser_modified(User user_modified) {
		User_modified = user_modified;
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

	public User getUser_deleted() {
		return User_deleted;
	}

	public void setUser_deleted(User user_deleted) {
		User_deleted = user_deleted;
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

}
