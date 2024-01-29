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
@Table(name = "m_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "biodata_id")
	private Long biodataId;
	
	@Column(name = "role_id")
	private Long roleId;

	@ManyToOne
	@JoinColumn(name = "biodata_id", insertable = false, updatable = false)
	private Biodata biodata;

	@ManyToOne
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private Role role;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "login_attempt")
	private Integer loginAttempt;

	@Column(name = "is_locked")
	private Boolean isLocked;

	@Column(name = "last_login")
	private LocalDateTime lastLogin;
	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "created_on")
	private LocalDateTime createdOn;


	@Column(name = "modified_by")
	private Long modifiedBy;

	@Column(name = "modified_on")
	private LocalDateTime modifiedOn;

	@Column(name = "deleted_by")
	private Long deletedBy;

	@Column(name = "deleted_on")
	private LocalDateTime deletedOn;
	
	@Column(name = "is_delete")
	private Boolean isDelete;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBiodataId() {
		return biodataId;
	}

	public void setBiodataId(Long biodataId) {
		this.biodataId = biodataId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Biodata getBiodata() {
		return biodata;
	}

	public void setBiodata(Biodata biodata) {
		this.biodata = biodata;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLoginAttempt() {
		return loginAttempt;
	}

	public void setLoginAttempt(Integer loginAttempt) {
		this.loginAttempt = loginAttempt;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
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
		return "User [id=" + id + ", biodataId=" + biodataId + ", roleId=" + roleId + ", email=" + email + ", password="
				+ password + ", loginAttempt=" + loginAttempt + ", isLocked=" + isLocked + ", lastLogin=" + lastLogin
				+ ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", isDelete=" + isDelete + ", modifiedBy="
				+ modifiedBy + ", modifiedOn=" + modifiedOn + ", deletedBy=" + deletedBy + ", deletedOn=" + deletedOn
				+ "]";
	}
	
}
