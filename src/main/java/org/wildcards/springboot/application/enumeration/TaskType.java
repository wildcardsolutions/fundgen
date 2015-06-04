package org.wildcards.springboot.application.enumeration;


public enum TaskType {
	REQUEST_MEMBERSHIP_CARDS(1, "Request Membership Cards"),
	REGISTER_NEW_MEMBER(2, "Register New Member")
	;
	
	private int id;
	
	private String description;
	
	private TaskType(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
