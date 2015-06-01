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
public class RequestForConfirmation extends AbstractRequestStatus {

	public RequestForConfirmation() {
		super("FOR CONFIRMATION");
	}

	@Override
	public void doAction(RequestContext context, RequestAction action) {
		switch (action) {
			case CONFIRM:
				break;
			case DENY:
				break;
			default:
				throw new RequestActionNotSupportedException();
		}

	}

}
