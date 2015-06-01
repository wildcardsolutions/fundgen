package org.wildcards.springboot.application.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.wildcards.springboot.application.form.role.AccessRightsForm;
import org.wildcards.springboot.infrastructure.rest.AbstractRestRequestHandler;
import org.wildcards.springboot.infrastructure.security.model.ApplicationResource;
import org.wildcards.springboot.infrastructure.security.model.Role;
import org.wildcards.springboot.infrastructure.security.repository.ApplicationResourceRepository;
import org.wildcards.springboot.infrastructure.security.repository.RoleRepository;


@RestController
@RequestMapping("/application-resources")
public class ResourceController  extends AbstractRestRequestHandler {

	/**
	 * 
	 */
	@Autowired
	private ApplicationResourceRepository resourceRepository;
	
	
	@Autowired
	private RoleRepository roleRepository;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public Map<String, List<ApplicationResource>> get() {
		List<ApplicationResource> resources =  resourceRepository.findAll();
		Map<String, List<ApplicationResource>> resultSet = new HashMap<String, List<ApplicationResource>>();
		for (ApplicationResource resource : resources) {
			List<ApplicationResource> resourceByClassifier = resultSet.get(resource.getClassifier());
			if (null==resourceByClassifier) {
				resourceByClassifier = new ArrayList<ApplicationResource>();
			}
			resourceByClassifier.add(resource);
			resultSet.put(resource.getClassifier(), resourceByClassifier);
		}
		return resultSet;
    } 
	
	
	@RequestMapping(
			value = "/{roleId}",
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public  Map<String, Map<String, Boolean>> getResourceForRole(@PathVariable(value="roleId") Long roleId) {
		System.out.println("roleId=" + roleId);
		Role role = roleRepository.getOne(roleId);
		List<ApplicationResource> resources =  resourceRepository.findAll();
		Map<String, Map<String, Boolean>> resultSet = new HashMap<String, Map<String, Boolean>>();
		for (ApplicationResource resource : resources) {
			Map<String, Boolean> resourceByClassifier = resultSet.get(resource.getClassifier());
			if (null==resourceByClassifier) {
				resourceByClassifier = new HashMap<String, Boolean>();
			}
			Boolean hasAccessToResource = false;
			for (ApplicationResource resourceAccess : role.getApplicationResources()) {
				if (resource.getResourceId()==resourceAccess.getResourceId()) {
					hasAccessToResource = true;
					break;
				}
			}
			resourceByClassifier.put(resource.getResource(), hasAccessToResource);
			
			resultSet.put(resource.getClassifier(), resourceByClassifier);
		}
		return resultSet;

    } 
	
	
	@RequestMapping(
			value = "/{roleId}",
			method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public void updateResourceForRole(
    		@PathVariable(value="roleId") Long roleId, 
    		@RequestBody AccessRightsForm form) {
		
		Role role = roleRepository.findOne(roleId);
		
		System.out.println("roleId=" + roleId);
		System.out.println(form.getResources());
		Set<ApplicationResource> resources = new HashSet<ApplicationResource>();
		 
		for (String resourceName : form.getResources().keySet()) {
			Map<String, Boolean> resourceMap = form.getResources();
			
			if (resourceMap.get(resourceName)) {
				ApplicationResource resource = resourceRepository.findResourceByName(resourceName);
				resources.add(resource);
			}
		}
		
		role.setApplicationResources(resources);
		roleRepository.saveAndFlush(role);
    } 
}
