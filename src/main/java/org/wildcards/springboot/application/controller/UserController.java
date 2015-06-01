package org.wildcards.springboot.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.wildcards.springboot.infrastructure.rest.AbstractRestRequestHandler;
import org.wildcards.springboot.infrastructure.security.model.UserProfile;
import org.wildcards.springboot.infrastructure.security.repository.UserProfileRepository;

/**
 * 
 * @author jojo
 *
 */
@RestController
@RequestMapping("/user")
public class UserController extends AbstractRestRequestHandler {

	@Autowired
	private UserProfileRepository userRepository;
	
	
	@RequestMapping(
			method=RequestMethod.GET,
			value="/{userid}")
	@ResponseStatus(HttpStatus.OK)
    public UserProfile getUser(@PathVariable Long userid) {
		return userRepository.getOne(userid);
    }
	
	

	@RequestMapping(
			method=RequestMethod.DELETE,
			value="/{userid}")
	@ResponseStatus(HttpStatus.OK)
    public String deleteUser(@PathVariable Long userid) {
        return "Boo!";
    }
	
	
	@RequestMapping(
			method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public void createUser(@RequestBody UserProfile user) {
		userRepository.saveAndFlush(user);
    }
	
	@RequestMapping(
			method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody UserProfile user) {
        
    }
	
}

