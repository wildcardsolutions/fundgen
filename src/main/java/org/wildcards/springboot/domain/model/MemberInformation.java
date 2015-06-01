package org.wildcards.springboot.domain.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="member")
public class MemberInformation extends AbstractModel {

	@Column(nullable = false)
	private String firstname;
	
	@Column(nullable = false)
	private String middlename;
	
	@Column(nullable = false)
	private String lastname;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dateOfBirth;
	
	@Column(nullable = false)
	private int age;
	
	@Column
	private String address;
	
	@Column
	private String cellphoneNumber;
	
	@Column
	private String homephoneNumber;
	
	@Column
	private String officephoneNumber;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "member_cards", 
			joinColumns = { @JoinColumn(name = "member.id") }, 
			inverseJoinColumns = { @JoinColumn(name = "member_cards.id") })
	private List<MembershipCard> membershipCards;

	@Transient
	private MembershipDetails membership;
	
	public MembershipDetails getMembership() {
		return membership;
	}

	public void setMembership(MembershipDetails membership) {
		this.membership = membership;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCellphoneNumber() {
		return cellphoneNumber;
	}

	public void setCellphoneNumber(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
	}

	public String getHomephoneNumber() {
		return homephoneNumber;
	}

	public void setHomephoneNumber(String homephoneNumber) {
		this.homephoneNumber = homephoneNumber;
	}

	public String getOfficephoneNumber() {
		return officephoneNumber;
	}

	public void setOfficephoneNumber(String officephoneNumber) {
		this.officephoneNumber = officephoneNumber;
	}

	public List<MembershipCard> getMembershipCards() {
		return membershipCards;
	}

	public void setMembershipCards(List<MembershipCard> membershipCards) {
		this.membershipCards = membershipCards;
	}
	
	public String getFullName() {
		return lastname + ", " + firstname + " " + middlename;
	}
	
}
