package com.teama.minpro.teama.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="code")
	private String code;
	
	@Column(name="created_by")
	private Long createdBy;
	
	@Column(name="created_on")
	private LocalDateTime createdOn;
	
	@Column(name="modified_by")
	private Long modifiedBy;
	
	@Column(name="modified_on")
	private LocalDateTime modifiedOn;
	
	@Column(name="deleted_by")
	private Long deletedBy;
	
	@Column(name="deleted_on")
	private LocalDateTime deletedOn;
	
	@Column(name="is_delete")
	private Boolean isDelete;

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
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



	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", code=" + code + ", createdBy=" + createdBy + ", createdOn="
				+ createdOn + ", isDelete=" + isDelete + ", modifiedBy=" + modifiedBy + ", modifiedOn=" + modifiedOn
				+ ", deletedBy=" + deletedBy + ", deletedOn=" + deletedOn + "]";
	}
	
	
	



}
