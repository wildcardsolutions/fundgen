package org.wildcards.springboot.domain.model.request;

import java.util.Date;
import java.util.List;

import org.wildcards.springboot.domain.model.AbstractModel;
import org.wildcards.springboot.domain.model.request.status.RequestCreated;

public class RequestContext extends AbstractModel {

	/**
	 * 
	 */
	private RequestStatus requestStatus;
	
	/**
	 * 
	 */
	private List<RequestHistory> requestHistory;
	
	/**
	 * 
	 */
	private String requestingChapter;
	
	/**
	 * 
	 */
	private String requestingOfficer;
	
	/**
	 * 
	 */
	private Date dateRequested;
	
	
	
	
	/**
	 * 
	 */
	private RequestContext() {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static RequestContext createRequest() {
		RequestContext context = new RequestContext();
		context.changeState(new RequestCreated());
		return context;
	}
	
	
	public static RequestContext request() {
		RequestContext context = new RequestContext();
		context.changeState(new RequestCreated());
		return context;
	}

	public void doAction(RequestAction action) {
		this.requestStatus.doAction(this, action);
	}
	
	public void changeState(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

	public List<RequestHistory> getRequestHistory() {
		return requestHistory;
	}

	public void setRequestHistory(List<RequestHistory> requestHistory) {
		this.requestHistory = requestHistory;
	}
}
