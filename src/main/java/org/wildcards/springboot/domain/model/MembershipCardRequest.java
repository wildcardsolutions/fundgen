package org.wildcards.springboot.domain.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="membership_card_request")
public class MembershipCardRequest extends AbstractModel {
	
	@OneToOne(cascade = CascadeType.ALL)
	private Chapter chapter; 
	

	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "map_request_status", 
//			joinColumns = { @JoinColumn(name = "member.id") }, 
//			inverseJoinColumns = { @JoinColumn(name = "member_cards.id") })
//	private List<MembershipCardRequestStatus> listOfRequestStatus;
//	
	
}
