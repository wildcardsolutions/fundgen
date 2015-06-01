package org.wildcards.springboot.application.dto;

public class MembershipCardListItem {
	
	private String cardNumber;
	
	private long cardId;
	
	private String cardType;
	
	private String assignedToChapter;
	
	private long assignedToChapterId;
	
	private String assignedToMember;
	
	private long assignedToMemberId;
	
	private boolean active;
	
	private boolean discarded;

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

	public String getAssignedToChapter() {
		return assignedToChapter;
	}

	public void setAssignedToChapter(String assignedToChapter) {
		this.assignedToChapter = assignedToChapter;
	}

	public String getAssignedToMember() {
		return assignedToMember;
	}

	public void setAssignedToMember(String assignedToMember) {
		this.assignedToMember = assignedToMember;
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

	public long getAssignedToChapterId() {
		return assignedToChapterId;
	}

	public void setAssignedToChapterId(long assignedToChapterId) {
		this.assignedToChapterId = assignedToChapterId;
	}

	public long getAssignedToMemberId() {
		return assignedToMemberId;
	}

	public void setAssignedToMemberId(long assignedToMemberId) {
		this.assignedToMemberId = assignedToMemberId;
	}

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}
	
}
