package org.wildcards.springboot.infrastructure.security.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.wildcards.springboot.infrastructure.security.model.UserProfile;
import org.wildcards.springboot.infrastructure.security.repository.UserProfileRepository;

/**
 * 
 * @author jojo
 *
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {

	/**
	 * 
	 */
	@Autowired
	private UserProfileRepository userRepository;
	
	/**
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserProfile person = userRepository.findByUsernameEquals(username);
		
		if (person == null) {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
		
		System.out.println("username=" + person.getUsername());
		
		return new org.springframework.security.core.userdetails.User(
				person.getUsername(), 
				person.getPassword(), 
				AuthorityUtils.createAuthorityList(person.getRole().getRoleName()));
	}
	
	
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	private Collection<? extends GrantedAuthority> getGrantedAuthorities(String username) {
		Collection<? extends GrantedAuthority> authorities = null;
		AuthorityUtils.createAuthorityList("");

		return authorities;
	}

}
