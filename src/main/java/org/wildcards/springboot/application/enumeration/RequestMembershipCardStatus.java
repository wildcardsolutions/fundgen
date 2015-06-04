package org.wildcards.springboot.application.enumeration;

public enum RequestMembershipCardStatus {
	NEW(1, "New"),
	DONE(2, "Done")
	;
	
	private int id;
	
	private String description;
	
	private RequestMembershipCardStatus(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
