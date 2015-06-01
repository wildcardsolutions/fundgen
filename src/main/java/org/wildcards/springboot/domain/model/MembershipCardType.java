package org.wildcards.springboot.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author jojo
 *
 */
@Entity
@Table(name="membership_card_type")
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
			name="deleteCardType", 
			procedureName="delete_cardtype", 
			parameters={
					@StoredProcedureParameter(type = Long.class),
					@StoredProcedureParameter(type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(
			name="undeleteCardType", 
			procedureName="undelete_cardtype", 
			parameters={
					@StoredProcedureParameter(type = Long.class),
					@StoredProcedureParameter(type = Long.class)
			}
	)
})
@JsonIgnoreProperties({"dateCreated", "createdBy", "dateDeleted", "deletedBy", "dateModified", "modifiedBy" })
public class MembershipCardType extends AbstractModel {
	
	@Column
	private String name;
	
	@Column
	private String prefix;
	
	@Column
	private int minimumAge;
	
	@Column
	private int maximumAge;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public int getMinimumAge() {
		return minimumAge;
	}

	public void setMinimumAge(int minimumAge) {
		this.minimumAge = minimumAge;
	}

	public int getMaximumAge() {
		return maximumAge;
	}

	public void setMaximumAge(int maximumAge) {
		this.maximumAge = maximumAge;
	}

	
}
