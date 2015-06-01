package org.wildcards.springboot.application.form.membership;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author jojo
 *
 */
public class MembershipRegistrationForm {

	private String firstname;
	
	private String middlename;
	
	private String lastname;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date dateOfBirth;
	
	private int age;
	
	private String address;
	
	private String emailAddress;
	
	private String cellphoneNumber;
	
	private String homephoneNumber;
	
	private String officephoneNumber;
	
	private long declarationPeriod;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date effectivityDate;
	
	private long cardType;
	
	private long cardNumber;

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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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

	public long getDeclarationPeriod() {
		return declarationPeriod;
	}

	public void setDeclarationPeriod(long declarationPeriod) {
		this.declarationPeriod = declarationPeriod;
	}

	public Date getEffectivityDate() {
		return effectivityDate;
	}

	public void setEffectivityDate(Date effectivityDate) {
		this.effectivityDate = effectivityDate;
	}

	public long getCardType() {
		return cardType;
	}

	public void setCardType(long cardType) {
		this.cardType = cardType;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}


	
}
