package org.wildcards.springboot.domain.model.request;

public interface RequestStatus {

	void doAction(RequestContext context, RequestAction action);

}
