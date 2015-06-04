package org.wildcards.springboot.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author jojo
 *
 */
@Entity
@Table(name="membership_card_request")
public class MembershipCardRequest extends AbstractModel {
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "membership_card_request_chapter", 
			joinColumns = { @JoinColumn(name = "requestId") }, 
			inverseJoinColumns = { @JoinColumn(name = "chapterId") })
	private Chapter chapter;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "membership_card_request_officer", 
			joinColumns = { @JoinColumn(name = "requestId") }, 
			inverseJoinColumns = { @JoinColumn(name = "officerId") })
	private Officer officer;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name= "request_id", nullable=false)
	private List<MembershipCardRequestItem> requestedCards;
	
	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "map_request_status", 
//			joinColumns = { @JoinColumn(name = "member.id") }, 
//			inverseJoinColumns = { @JoinColumn(name = "member_cards.id") })
//	private List<MembershipCardRequestStatus> listOfRequestStatus;
//	
	

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public Officer getOfficer() {
		return officer;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

	public List<MembershipCardRequestItem> getRequestedCards() {
		return requestedCards;
	}

	public void setRequestedCards(List<MembershipCardRequestItem> requestedCards) {
		this.requestedCards = requestedCards;
	}
}
