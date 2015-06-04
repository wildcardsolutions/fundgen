package org.wildcards.springboot.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.wildcards.springboot.application.form.request.MembershipCardRequestForm;
import org.wildcards.springboot.infrastructure.rest.AbstractRestRequestHandler;

@RestController
@RequestMapping("/membershipCardRequest")
public class MembershipCardRequestController extends AbstractRestRequestHandler {

	/**
	 * 
	 * @param requestForm
	 */
	@RequestMapping(
			method=RequestMethod.POST,
			value="/create")
	@ResponseStatus(HttpStatus.OK)
	public MembershipCardRequestForm createRequest(
			@RequestBody MembershipCardRequestForm requestForm) {
		
		
		
		return requestForm;
	}
	
	@RequestMapping(
			method=RequestMethod.POST,
			value="/submit")
	@ResponseStatus(HttpStatus.OK)
	public MembershipCardRequestForm submitRequest(
			@RequestBody MembershipCardRequestForm requestForm) {
		
		return requestForm;
	}
	
	@RequestMapping(
			method=RequestMethod.POST,
			value="/cancel")
	@ResponseStatus(HttpStatus.OK)
	public MembershipCardRequestForm cancelRequest(
			@RequestBody MembershipCardRequestForm requestForm) {
		
		return requestForm;
	}
	
	@RequestMapping(
			method=RequestMethod.POST,
			value="/deny")
	@ResponseStatus(HttpStatus.OK)
	public void denyRequest(@RequestBody MembershipCardRequestForm requestForm) {
		
	}
	
	@RequestMapping(
			method=RequestMethod.POST,
			value="/approve")
	@ResponseStatus(HttpStatus.OK)
	public void approveRequest(@RequestBody MembershipCardRequestForm requestForm) {
		
	}
	
	
	@RequestMapping(
			method=RequestMethod.POST,
			value="/process")
	@ResponseStatus(HttpStatus.OK)
	public void processRequest(
			@RequestBody MembershipCardRequestForm requestForm) {
		
	}
}
