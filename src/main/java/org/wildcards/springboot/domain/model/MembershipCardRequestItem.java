package org.wildcards.springboot.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="membership_card_request_item")
public class MembershipCardRequestItem  {
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "membershipCardType.id", nullable=false)
	private MembershipCardType cardTypeRequested;
	
	@Column
	private Long itemsRequested;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MembershipCardType getCardTypeRequested() {
		return cardTypeRequested;
	}

	public void setCardTypeRequested(MembershipCardType cardTypeRequested) {
		this.cardTypeRequested = cardTypeRequested;
	}
	
	public Long getItemsRequested() {
		return itemsRequested;
	}

	public void setItemsRequested(Long itemsRequested) {
		this.itemsRequested = itemsRequested;
	}
}
