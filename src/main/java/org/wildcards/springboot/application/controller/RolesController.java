package org.wildcards.springboot.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.wildcards.springboot.infrastructure.rest.AbstractRestRequestHandler;
import org.wildcards.springboot.infrastructure.security.model.Role;
import org.wildcards.springboot.infrastructure.security.repository.RoleRepository;

/**
 * 
 * @author jojo
 *
 */
@RestController
@RequestMapping("/api/role")
public class RolesController extends AbstractRestRequestHandler {

	/**
	 * 
	 */
	@Autowired
	private RoleRepository repository;
	
	@RequestMapping(
			value = "/getPageCountBySize/{size}",
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public Object getPageCountBySize(
    		@PathVariable(value="size") int size) {
		
		long count = repository.getCount();
		long mod = count % size;
		long page = (count / size) + (mod == 0 ? 0 : 1);
		
		return "{\"pages\" : \"" + page + "\"}";
    }
	
	@RequestMapping(
			value = "/page/{page}/{size}",
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getPage(
    		@PathVariable(value="page") int page,
    		@PathVariable(value="size") int size) {
		Page<Role> allRoles = repository.findAll(new PageRequest(page-1, size));
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("totalElements", allRoles.getTotalElements());
		result.put("totalPages", allRoles.getTotalPages());
		result.put("pageIndex", page);
		result.put("pageContent", allRoles.getContent());
		
		return result;
    }
	
	
	@RequestMapping(
			value = "/page/{page}/{size}/{searchString}",
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getPage(
    		@PathVariable(value="page") int page,
    		@PathVariable(value="size") int size,
    		@PathVariable(value="searchString") String searchString) {
		Map<String, Object> result = new HashMap<String, Object>();
		Page<Role> matchingRoles = repository.searchFor(searchString, new PageRequest(page-1, size));
		
		result.put("totalElements", matchingRoles.getTotalElements());
		result.put("totalPages", matchingRoles.getTotalPages());
		result.put("pageIndex", page);
		result.put("pageContent", matchingRoles.getContent());
		
		return result;
    }
	
	/**
	 * 
	 * @param search
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(
			value = "/search/{search}/{page}/{size}",
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public Map<String, Object> searchFor(
    		@PathVariable(value="search") String search,
    		@PathVariable(value="page") int page,
    		@PathVariable(value="size") int size) {
			Page<Role> matchingRoles = repository.searchFor(search, new PageRequest(page-1, size));
			
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("totalElements", matchingRoles.getTotalElements());
			result.put("totalPages", matchingRoles.getTotalPages());
			result.put("pageIndex", page);
			result.put("pageContent", matchingRoles.getContent());
			
		return result;
    }
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<Role> getAllRoles() {
		return repository.findAll();
    }
	
	/**
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(
			method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public Role addRole(@RequestBody Role role) {
		return repository.save(role);
    }
	
	/**
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(
			value = "/{id}",
			method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
    public Role updateRole(
    		@PathVariable(value="id") Long id,
    		@RequestBody Role updatedRole) {

		Role role = repository.getOne(id);
		role.setRoleDescription(updatedRole.getRoleDescription());
		role.setRoleName(updatedRole.getRoleName());
		
		return repository.save(role);
    }
	
	/**
	 * 
	 * @param id
	 */
	@RequestMapping(
			value = "/{id}",
			method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(value="id") Long id) {
		System.out.println("id=" + id);
		Long userId = 1L;
		repository.delete(id, userId);
    }
	
	/**
	 * 
	 * @param id
	 */
	@RequestMapping(
			value = "/undelete/{id}",
			method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public void undelete(@PathVariable(value="id") Long id) {
		System.out.println("id=" + id);
		Long userId = 1L;
		repository.undelete(id, userId);
    }
}
