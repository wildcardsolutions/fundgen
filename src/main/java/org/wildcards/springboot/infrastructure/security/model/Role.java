package org.wildcards.springboot.infrastructure.security.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author jojo
 *
 */
@Entity
@Table(name="roles")
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
			name="deleteRole", 
			procedureName="delete_role", 
			parameters={
					@StoredProcedureParameter(type = Long.class),		// roleId
					@StoredProcedureParameter(type = Long.class)		// userId
			}
	),
	@NamedStoredProcedureQuery(
			name="undeleteRole", 
			procedureName="undelete_role", 
			parameters={
					@StoredProcedureParameter(type = Long.class),		// roleId
					@StoredProcedureParameter(type = Long.class)		// userId
			}
	)
})
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long roleId;
	
	
	@Column(nullable = false, unique = true)
	private String roleName;
	
	@Column(nullable = false)
	private String roleDescription;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date dateCreated;
	
	@Column(nullable = true)
	private Long createdBy;
	
	@Column(nullable = true, columnDefinition="boolean default false")
	private boolean deleted;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date dateDeleted;
	
	@Column(nullable = true)
	private Long deletedBy;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date dateModified;
	
	@Column(nullable = true)
	private Long modifiedBy;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "role_resource", 
			joinColumns = { @JoinColumn(name = "roleId") }, 
			inverseJoinColumns = { @JoinColumn(name = "resourceId") })
    private Set<ApplicationResource> applicationResources;
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
	public Set<ApplicationResource> getApplicationResources() {
		return applicationResources;
	}

	public void setApplicationResources(Set<ApplicationResource> applicationResources) {
		this.applicationResources = applicationResources;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getDateDeleted() {
		return dateDeleted;
	}

	public void setDateDeleted(Date dateDeleted) {
		this.dateDeleted = dateDeleted;
	}

	public Long getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(Long deletedBy) {
		this.deletedBy = deletedBy;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
