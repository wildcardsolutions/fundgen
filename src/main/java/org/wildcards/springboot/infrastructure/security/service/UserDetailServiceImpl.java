package org.wildcards.springboot.infrastructure.security.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.wildcards.springboot.infrastructure.security.model.UserProfile;
import org.wildcards.springboot.infrastructure.security.repository.UserProfileRepository;

/**
 * 
 * @author jojo
 *
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserProfileRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserProfile person = userRepository.findByUsernameEquals(username);
		System.out.println("username=" + username);
		if (person == null) {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
		return new org.springframework.security.core.userdetails.User(username, "password", getGrantedAuthorities(username));
	}
	
	
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	private Collection<? extends GrantedAuthority> getGrantedAuthorities(String username) {
		Collection<? extends GrantedAuthority> authorities = null;
//		if (username.equals("John")) {
//			authorities = asList(() -> "ROLE_ADMIN", () -> "ROLE_BASIC");
//		} else {
//			authorities = asList(() -> "ROLE_BASIC");
//		}
		return authorities;
	}

}
