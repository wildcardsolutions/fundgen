package org.wildcards.springboot.infrastructure.security.service;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
public class UserAuthenticationProvider implements AuthenticationProvider {

	/**
	 * 
	 */
	private static final String INVALID_USER_CREDENTIALS = "Invalid User Credentials";

	/**
	 * 
	 */
	private Logger logger = Logger.getLogger(UserAuthenticationProvider.class);
	
	/**
	 * 
	 */
	@Autowired
	private UserProfileRepository userRepository;
	
	/**
	 * 
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("authentication=" + authentication);
		System.out.println("name=" + authentication.getName());
		System.out.println("principal=" + authentication.getPrincipal());
		System.out.println("credentials=" + authentication.getCredentials());
		
		System.out.println(System.getProperty("LOG_FILE"));
		
		Optional<Object> username = Optional.ofNullable(authentication.getPrincipal());
		Optional<Object> password = Optional.ofNullable(authentication.getCredentials());
		logger.info("username=" + username.get().toString());
		throwExceptionIfNotPresent(username);
		throwExceptionIfNotPresent(password);
		
		UserProfile user = userRepository.findByUsernameEquals(username.get().toString());
		throwExceptionIfNull(user);
		throwExceptionIfPasswordNotEqual(user.getPassword(), password.get().toString());
	    System.out.println("test ok");
	    
	    
		return null;
	}

	

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	
	/**
	 * 
	 * @param password
	 * @param string
	 */
	private void throwExceptionIfPasswordNotEqual(String passwordFromDB, String passwordFromClient) {
		System.out.println("throwExceptionIfPasswordNotEqual");
		if (!passwordFromDB.equals(passwordFromClient)) {
			throw new BadCredentialsException(INVALID_USER_CREDENTIALS);
		}
	}

	/**
	 * 
	 * @param user
	 */
	private void throwExceptionIfNull(Object user) {
		System.out.println("throwExceptionIfNull");
		if (null == user) {
			throw new BadCredentialsException(INVALID_USER_CREDENTIALS);
		}
	}

	/**
	 * 
	 * @param username
	 */
	private void throwExceptionIfNotPresent(Optional<?> object) {
		System.out.println("throwExceptionIfNotPresent");
		if (!object.isPresent() || object.get().toString().isEmpty()) {
            throw new BadCredentialsException(INVALID_USER_CREDENTIALS);
        }
	}
}
