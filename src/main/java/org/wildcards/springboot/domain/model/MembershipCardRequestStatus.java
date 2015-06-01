package org.wildcards.springboot.domain.model;

import java.util.Date;

/**
 * 
 * @author jojo
 *
 */
public class MembershipCardRequestStatus {
	
	private MembershipCardRequestStatusType status;
	
	private Officer assignedOfficer;
	
	private Date dateAssigned;
	
	private Date dateProcessed;
	
}
