package org.wildcards.springboot.application.dto;


public class MembershipCardDTO {
	
	private long cardId;
	
	private String cardNumber;
	
	private String cardType;

	private String dateCreated;
	
	private String assignedToChapter;
	
	private String dateAssignedToChapter;
	
	private String assignedToMember;
	
	private String dateAssignedToMember;
	
	private boolean active;
	
	private boolean discarded;

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getAssignedToChapter() {
		return assignedToChapter;
	}

	public void setAssignedToChapter(String assignedToChapter) {
		this.assignedToChapter = assignedToChapter;
	}

	public String getDateAssignedToChapter() {
		return dateAssignedToChapter;
	}

	public void setDateAssignedToChapter(String dateAssignedToChapter) {
		this.dateAssignedToChapter = dateAssignedToChapter;
	}

	public String getAssignedToMember() {
		return assignedToMember;
	}

	public void setAssignedToMember(String assignedToMember) {
		this.assignedToMember = assignedToMember;
	}

	public String getDateAssignedToMember() {
		return dateAssignedToMember;
	}

	public void setDateAssignedToMember(String dateAssignedToMember) {
		this.dateAssignedToMember = dateAssignedToMember;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isDiscarded() {
		return discarded;
	}

	public void setDiscarded(boolean discarded) {
		this.discarded = discarded;
	}
}
