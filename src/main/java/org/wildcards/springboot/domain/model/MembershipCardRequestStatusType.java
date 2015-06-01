package org.wildcards.springboot.domain.model;

/**
 * 
 * @author jojo
 *
 */
public enum MembershipCardRequestStatusType {
	NEW,
	FOR_CHAPTER_APPROVAL,
	CHAPTER_APPROVAL_DENIED,
	FOR_NHQ_APPROVAL,
	NHQ_APPROVAL_DENIED,
	FOR_PROCESSING,
	PROCESSING_DENIED,
	FOR_CONFIRMATION,
	CONFIRMATION_DENIED,
	CLOSED;
}
