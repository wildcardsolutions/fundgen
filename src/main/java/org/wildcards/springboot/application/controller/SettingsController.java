package org.wildcards.springboot.application.controller;

import java.util.Date;
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
import org.wildcards.springboot.domain.model.SystemSetting;
import org.wildcards.springboot.domain.repository.SettingsRepository;
import org.wildcards.springboot.infrastructure.rest.AbstractRestRequestHandler;
import org.wildcards.springboot.infrastructure.security.model.Role;

/**
 * 
 * @author jojo
 *
 */
@RestController
@RequestMapping("/api/settings")
public class SettingsController extends AbstractRestRequestHandler {

	/**
	 * 
	 */
	@Autowired
	private SettingsRepository repository;
	
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
		Page<SystemSetting> all = repository.findAll(new PageRequest(page-1, size));
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("totalElements", all.getTotalElements());
		result.put("totalPages", all.getTotalPages());
		result.put("pageIndex", page);
		result.put("pageContent", all.getContent());
		
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
		Page<SystemSetting> matches = repository.searchFor(searchString, new PageRequest(page-1, size));
		
		result.put("totalElements", matches.getTotalElements());
		result.put("totalPages", matches.getTotalPages());
		result.put("pageIndex", page);
		result.put("pageContent", matches.getContent());
		
		return result;
    }
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<SystemSetting> getAllSettings() {
		return repository.findAll();
    }
	
	
	
	@RequestMapping(
			method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public SystemSetting addSetting(@RequestBody SystemSetting setting) {
		Long userId = 1L;
		setting.setCreatedBy(userId);
		setting.setDateCreated(new Date());
		return repository.save(setting);
    }
	
	
	@RequestMapping(
			method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
    public SystemSetting updateSetting(@RequestBody SystemSetting setting) {
		Long userId = 1L;
		setting.setModifiedBy(userId);
		setting.setDateModified(new Date());
		return repository.save(setting);
    }
	
	
	@RequestMapping(
			value = "/{settingId}",
			method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
    public void deleteSetting(@PathVariable(value="settingId") Long settingId) {
		System.out.println("settingId=" + settingId);
		Long userId = 1L;
		repository.delete(settingId, userId);
    }
	
	@RequestMapping(
			value = "/undelete/{settingId}",
			method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public void undeleteSetting(@PathVariable(value="settingId") Long settingId) {
		System.out.println("settingId=" + settingId);
		Long userId = 1L;
		repository.undelete(settingId, userId);
    }
}
