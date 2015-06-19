package org.wildcards.springboot.application.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wildcards.springboot.infrastructure.rest.AbstractRestRequestHandler;

/**
 * 
 * @author jojo
 *
 */
@RestController

public class UserController extends AbstractRestRequestHandler {


	  @RequestMapping("/user")
	  public Principal user(Principal user) {
		  return user;
	  }
	
//	@Autowired
//	private UserProfileRepository userRepository;
//	
//	
//	@RequestMapping(
//			method=RequestMethod.GET,
//			value="/{userid}")
//	@ResponseStatus(HttpStatus.OK)
//    public UserProfile getUser(@PathVariable Long userid) {
//		return userRepository.getOne(userid);
//    }
//	
//	
//
//	@RequestMapping(
//			method=RequestMethod.DELETE,
//			value="/{userid}")
//	@ResponseStatus(HttpStatus.OK)
//    public String deleteUser(@PathVariable Long userid) {
//        return "Boo!";
//    }
//	
//	
//	@RequestMapping(
//			method=RequestMethod.POST)
//	@ResponseStatus(HttpStatus.OK)
//    public void createUser(@RequestBody UserProfile user) {
//		userRepository.saveAndFlush(user);
//    }
//	
//	@RequestMapping(
//			method=RequestMethod.PUT)
//	@ResponseStatus(HttpStatus.OK)
//    public void updateUser(@RequestBody UserProfile user) {
//        
//    }
	
}

