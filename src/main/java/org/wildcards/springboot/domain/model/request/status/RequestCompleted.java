package org.wildcards.springboot.domain.model.request.status;

import org.wildcards.springboot.domain.model.request.RequestAction;
import org.wildcards.springboot.domain.model.request.RequestContext;
import org.wildcards.springboot.domain.model.request.RequestStatus;

/**
 * 
 * @author jojo
 *
 */
public class RequestCompleted extends AbstractRequestStatus {

	public RequestCompleted() {
		super("COMPLETED");
	}

	@Override
	public void doAction(RequestContext context, RequestAction action) {
		
	}

}
