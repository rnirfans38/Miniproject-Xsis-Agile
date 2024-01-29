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

import org.springframework.beans.factory.annotation.Autowired;

//nama tabel harus representatif ke isi (penting)
@Entity
@Table(name = "rumah_makan")
public class RumahMakan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long id;

	@Column(name = "menu_id")
	public Long menuId;

	@Column(name = "pembeli")
	public String pembeli;

	@Column(name = "created_by")
	public String createdBy;

	@Column(name = "created_on")
	public LocalDateTime createdOn;

	@Column(name = "modified_by")
	public String modifiedBy;

	@Column(name = "modified_on")
	public LocalDateTime modifiedOn;

	@Column(name = "deleted_by")
	public String deletedBy;

	@Column(name = "deleted_on")
	public LocalDateTime deletedOn;

	@Column(name = "is_delete")
	public Boolean isDelete;

	@Autowired
	// JOIN MENU MAKANAN
	@ManyToOne
	@JoinColumn(name = "menu_id", insertable = false, updatable = false)
	public MenuMakanan mm;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getPembeli() {
		return pembeli;
	}

	public void setPembeli(String pembeli) {
		this.pembeli = pembeli;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
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

	public MenuMakanan getMm() {
		return mm;
	}

	public void setMm(MenuMakanan mm) {
		this.mm = mm;
	}

}
