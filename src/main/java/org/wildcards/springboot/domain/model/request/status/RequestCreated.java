package org.wildcards.springboot.domain.model.request.status;

import org.wildcards.springboot.domain.model.request.RequestAction;
import org.wildcards.springboot.domain.model.request.RequestContext;

/**
 * 
 * @author jojo
 *
 */
public class RequestCreated extends AbstractRequestStatus {

	public RequestCreated() {
		super("NEW");
	}

	@Override
	public void doAction(RequestContext context, RequestAction action) {
	
	}

}
