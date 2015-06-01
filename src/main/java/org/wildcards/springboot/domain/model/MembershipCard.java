package org.wildcards.springboot.domain.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="membership_card")
@JsonInclude(Include.NON_NULL)
public class MembershipCard extends AbstractModel {
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "card_type", 
			joinColumns = { @JoinColumn(name = "membership_card.id") }, 
			inverseJoinColumns = { @JoinColumn(name = "card_type.id") })
	private MembershipCardType membershipCardType;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "card_chapter", 
			joinColumns = { @JoinColumn(name = "membership_card.id") }, 
			inverseJoinColumns = { @JoinColumn(name = "chapter.id") })
	private Chapter assignedToChapter;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "card_member", 
			joinColumns = { @JoinColumn(name = "membership_card.id") }, 
			inverseJoinColumns = { @JoinColumn(name = "member.id") })
	private MemberInformation assignedToMember;
	
	
	@Column(nullable = false, updatable = false, unique=true)
	private String cardId;
	
	@Column(nullable = false, updatable = false)
	private long idNumber;
	
	@Column
	private long cardType;
	
	@Column
	private boolean active;
	
	@Column
	private Date effectivityDate;
	
	@Column
	private Date dateAssignedToMember;
	
	@Column
	private Long assignedToMemberBy;

	@Column
	private Date dateAssignedToChapter;
	
	@Column
	private Long assignedToChapterBy;
	
	@Column
	private Date dateDiscarded;
	
	@Column
	private Long discardedBy;
	
	@Column
	private String reasonDiscarded;

	public MembershipCardType getMembershipCardType() {
		return membershipCardType;
	}

	public void setMembershipCardType(MembershipCardType membershipCardType) {
		this.membershipCardType = membershipCardType;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public long getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(long idNumber) {
		this.idNumber = idNumber;
	}

	public long getCardType() {
		return cardType;
	}

	public void setCardType(long cardType) {
		this.cardType = cardType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getEffectivityDate() {
		return effectivityDate;
	}

	public void setEffectivityDate(Date effectivityDate) {
		this.effectivityDate = effectivityDate;
	}

	public Date getDateAssignedToMember() {
		return dateAssignedToMember;
	}

	public void setDateAssignedToMember(Date dateAssignedToMember) {
		this.dateAssignedToMember = dateAssignedToMember;
	}

	public MemberInformation getAssignedToMember() {
		return assignedToMember;
	}

	public void setAssignedToMember(MemberInformation assignedToMember) {
		this.assignedToMember = assignedToMember;
	}

	public Long getAssignedToMemberBy() {
		return assignedToMemberBy;
	}

	public void setAssignedToMemberBy(Long assignedToMemberBy) {
		this.assignedToMemberBy = assignedToMemberBy;
	}

	public Date getDateAssignedToChapter() {
		return dateAssignedToChapter;
	}

	public void setDateAssignedToChapter(Date dateAssignedToChapter) {
		this.dateAssignedToChapter = dateAssignedToChapter;
	}

	

	public Long getAssignedToChapterBy() {
		return assignedToChapterBy;
	}

	public void setAssignedToChapterBy(Long assignedToChapterBy) {
		this.assignedToChapterBy = assignedToChapterBy;
	}

	public Date getDateDiscarded() {
		return dateDiscarded;
	}

	public void setDateDiscarded(Date dateDiscarded) {
		this.dateDiscarded = dateDiscarded;
	}

	public Long getDiscardedBy() {
		return discardedBy;
	}

	public void setDiscardedBy(Long discardedBy) {
		this.discardedBy = discardedBy;
	}

	public String getReasonDiscarded() {
		return reasonDiscarded;
	}

	public void setReasonDiscarded(String reasonDiscarded) {
		this.reasonDiscarded = reasonDiscarded;
	}
	
	public Chapter getAssignedToChapter() {
		return assignedToChapter;
	}

	public void setAssignedToChapter(Chapter assignedToChapter) {
		this.assignedToChapter = assignedToChapter;
	}
}
