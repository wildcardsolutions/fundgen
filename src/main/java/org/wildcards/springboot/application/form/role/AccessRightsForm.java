package org.wildcards.springboot.application.form.role;

import java.util.Map;

/**
 * 
 * @author jojo
 *
 */
public class AccessRightsForm {

	private Map<String, Boolean> resources;

	public Map<String, Boolean> getResources() {
		return resources;
	}

	public void setResources(Map<String, Boolean> resources) {
		this.resources = resources;
	}
	
	
}
