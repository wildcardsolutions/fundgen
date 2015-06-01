package org.wildcards.springboot.domain.model.request.status;

import org.wildcards.springboot.domain.model.request.RequestAction;
import org.wildcards.springboot.domain.model.request.RequestContext;
import org.wildcards.springboot.domain.model.request.RequestStatus;

/**
 * 
 * @author jojo
 *
 */
public class RequestDeleted extends AbstractRequestStatus {

	public RequestDeleted() {
		super("DELETED");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction(RequestContext context, RequestAction action) {
		
	}

}
