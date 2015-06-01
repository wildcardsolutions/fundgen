package org.wildcards.springboot.domain.model.request.status;

import org.wildcards.springboot.domain.model.request.RequestAction;
import org.wildcards.springboot.domain.model.request.RequestActionNotSupportedException;
import org.wildcards.springboot.domain.model.request.RequestContext;
import org.wildcards.springboot.domain.model.request.RequestStatus;

/**
 * 
 * @author jojo
 *
 */
public class RequestForApproval extends AbstractRequestStatus {

	public RequestForApproval() {
		super("FOR APPROVAL");
	}

	@Override
	public void doAction(RequestContext context, RequestAction action) {
		switch (action) {
			case CANCEL:
				break;
			case DENY:
				break;
			default:
				throw new RequestActionNotSupportedException();
		}

	}

}
