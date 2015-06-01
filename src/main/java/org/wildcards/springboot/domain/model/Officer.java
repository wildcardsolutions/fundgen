package org.wildcards.springboot.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.wildcards.springboot.infrastructure.security.model.UserProfile;

@Entity
@Table(name="officer")
public class Officer extends AbstractModel {
	
	@Column(nullable = false)
	private String firstname;
	
	@Column(nullable = false)
	private String middlename;
	
	@Column(nullable = false)
	private String lastname;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "chapter_officers", 
			joinColumns = { @JoinColumn(name = "officerId") }, 
			inverseJoinColumns = { @JoinColumn(name = "chapterId") })
	private Chapter chapter;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "officer_profile", 
			joinColumns = { @JoinColumn(name = "officerId") }, 
			inverseJoinColumns = { @JoinColumn(name = "userId") })
	private UserProfile userProfile;

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
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

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	
	
}
