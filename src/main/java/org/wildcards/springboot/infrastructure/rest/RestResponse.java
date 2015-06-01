package org.wildcards.springboot.infrastructure.rest;

public class RestResponse {
	private String status;
	private String description;
	
	public static RestResponse create(RestResponseType status, String description) {
		if (null==status) {
			status = RestResponseType.SUCCESS;
		}
		return new RestResponse(status.toString(), description);
	}
	
	private RestResponse(String status, String description) {
		this.status = status;
		this.description = description;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
