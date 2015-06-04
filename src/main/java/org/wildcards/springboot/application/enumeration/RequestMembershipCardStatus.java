package org.wildcards.springboot.application.enumeration;

public enum RequestMembershipCardStatus {
	
	NEW(1, "New"),
	FOR_APPROVAL(2, "For Approval"),
	CLOSED(14, "Closed"),
	DONE(15, "Done")
	
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
