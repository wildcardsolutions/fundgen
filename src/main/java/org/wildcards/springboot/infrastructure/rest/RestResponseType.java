package org.wildcards.springboot.infrastructure.rest;

public enum RestResponseType {
	SUCCESS("Success"),
	FAILED("Failed");
	
	private String status;

	private RestResponseType(String status) {
		this.status = status;
	}
	
	public String toString() {
		return status;
	}
}
