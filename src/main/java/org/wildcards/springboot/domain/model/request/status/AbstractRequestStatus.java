package org.wildcards.springboot.domain.model.request.status;

import org.wildcards.springboot.domain.model.request.RequestStatus;

/**
 * 
 * @author jojo
 *
 */
public abstract class AbstractRequestStatus implements RequestStatus {

	private String status;
	
	public AbstractRequestStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
}
