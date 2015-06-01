package org.wildcards.springboot.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

/**
 * 
 * @author jojo
 *
 */
@Entity
@Table(name="system_setting")
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
			name="deleteSetting", 
			procedureName="delete_setting", 
			parameters={
					@StoredProcedureParameter(type = Long.class),
					@StoredProcedureParameter(type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(
			name="undeleteSetting", 
			procedureName="undelete_setting", 
			parameters={
					@StoredProcedureParameter(type = Long.class),
					@StoredProcedureParameter(type = Long.class)
			}
	)
})
public class SystemSetting extends AbstractModel {

	@Column(nullable = false, unique=true)
	private String name;
	
	@Column(nullable = false)
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
