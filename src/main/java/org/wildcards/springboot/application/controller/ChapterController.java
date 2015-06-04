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
import org.wildcards.springboot.domain.model.Chapter;
import org.wildcards.springboot.domain.repository.ChapterRepository;
import org.wildcards.springboot.infrastructure.rest.AbstractRestRequestHandler;

/**
 * 
 * @author jojo
 *
 */
@RestController
@RequestMapping("/api/chapter")
public class ChapterController extends AbstractRestRequestHandler {
	
	/**
	 * 
	 */
	@Autowired
	private ChapterRepository repository;
	
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
			value = "/getPageCountBySize/{size}/{searchString}",
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public Object getPageCountBySize(
    		@PathVariable(value="size") int size, 
    		@PathVariable(value="searchString") String searchString) {
		
		long count = repository.getCount(searchString);
		long mod = count % size;
		long page = (count / size) + (mod == 0 ? 0 : 1);
		
		return "{\"pages\" : \"" + page + "\"}";
    }
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(
			value = "/page/{page}/{size}",
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getPage(
    		@PathVariable(value="page") int page,
    		@PathVariable(value="size") int size) {
		Page<Chapter> allRoles = repository.findAll(new PageRequest(page-1, size));
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
		Page<Chapter> matchingRoles = repository.searchFor(searchString, new PageRequest(page-1, size));
		
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
    public List<Chapter> searchFor(
    		@PathVariable(value="search") String search,
    		@PathVariable(value="page") int page,
    		@PathVariable(value="size") int size) {
		return repository.searchFor(search, new PageRequest(page-1, size)).getContent();
    }
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<Chapter> getAllChapters() {
		return repository.findAll();
    }
	
	/**
	 * 
	 * @param setting
	 * @return
	 */
	@RequestMapping(
			method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public Chapter add(@RequestBody Chapter chapter) {
		Long userId = 1L;
		chapter.setCreatedBy(userId);
		chapter.setDateCreated(new Date());
		return repository.save(chapter);
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
    public Chapter update(
    		@PathVariable(value="id") Long id,
    		@RequestBody Chapter updatedChapter) {

		Chapter chapter = repository.getOne(id);
		chapter.setChapterName(updatedChapter.getChapterName());
		chapter.setChapterLocation(updatedChapter.getChapterLocation());
		
		return repository.save(chapter);
    }
	
	/**
	 * 
	 * @param settingId
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
    public void undeleteSetting(@PathVariable(value="id") Long id) {
		System.out.println("id=" + id);
		Long userId = 1L;
		repository.undelete(id, userId);
    }
}
