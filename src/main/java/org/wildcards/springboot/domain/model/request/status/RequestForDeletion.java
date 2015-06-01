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
public class RequestForDeletion extends AbstractRequestStatus {

	public RequestForDeletion() {
		super("FOR DELETION");
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
