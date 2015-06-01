package org.wildcards.springboot.infrastructure.security.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author jojo
 *
 */
@Entity
@Table(name="users")
public class UserProfile {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	protected Long userId;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@JsonIgnore
	@Column(nullable = false)
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", 
			joinColumns = { @JoinColumn(name = "userId") }, 
			inverseJoinColumns = { @JoinColumn(name = "roleId") })
    private Role role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
