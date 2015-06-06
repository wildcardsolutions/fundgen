/**
 * 
 */
package org.wildcards.springboot.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.wildcards.springboot.application.constant.Parameter;
import org.wildcards.springboot.domain.model.ParameterMap;
import org.wildcards.springboot.domain.model.Task;
import org.wildcards.springboot.domain.repository.TaskRepository;
import org.wildcards.springboot.domain.service.Service;
import org.wildcards.springboot.domain.service.validation.ValidationException;
import org.wildcards.springboot.infrastructure.rest.AbstractRestRequestHandler;

/**
 * 
 * @author jojo
 *
 */
@RestController
@RequestMapping("/api/task")
public class TaskController extends AbstractRestRequestHandler {

	/**
	 * 
	 */
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	@Qualifier("cancelTaskService")
	private Service<Task> cancelTaskService;
	
	
	@RequestMapping(
			value = "/getPageCountBySize/{size}",
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public Object getPageCountBySize(
    		@PathVariable(value="size") int size) {
		
		long count = taskRepository.getCount();
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
		
		long count = taskRepository.getCount(searchString);
		long mod = count % size;
		long page = (count / size) + (mod == 0 ? 0 : 1);
		
		return "{\"pages\" : \"" + page + "\"}";
    }
	
	@RequestMapping(
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<Task> findAll() {	
		return taskRepository.findAll();
    }
	

	@RequestMapping(
			value = "/{type}/{page}/{size}",
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getPage(
    		@PathVariable(value="type") String type,
    		@PathVariable(value="page") int page,
    		@PathVariable(value="size") int size) {
		
		Map<String, Object> mappedResult = new HashMap<String, Object>();
		Page<Task> pagedResult = null; 
		
		long chapterId = 2;
		long userId = 1;
		
		if ("all".equalsIgnoreCase(type)) {
			pagedResult = taskRepository.findAll(new PageRequest(page-1, size));
		} else if ("chapter".equalsIgnoreCase(type)) {
			pagedResult = taskRepository.getTasksByChapter(chapterId, new PageRequest(page-1, size));
		} else if ("my".equalsIgnoreCase(type)) {
			pagedResult = taskRepository.getTasksByOfficer(userId, new PageRequest(page-1, size));
		} else if ("history".equalsIgnoreCase(type)) {
			pagedResult = taskRepository.getTaskHistoryByChapter(chapterId, new PageRequest(page-1, size));
		} else {
			throw new ValidationException();
		}
		
		mappedResult.put("totalElements", pagedResult.getTotalElements());
		mappedResult.put("totalPages", pagedResult.getTotalPages());
		mappedResult.put("pageIndex", page);
		mappedResult.put("pageContent", pagedResult.getContent());
		
		return mappedResult;
    }
	
	
	@RequestMapping(
			value = "/cancel/{taskId}",
			method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public void cancelTask(
    		@PathVariable(value="taskId") long taskId) {
		
		long chapterId = 2;
		long userId = 1;

		ParameterMap params = new ParameterMap();
		params.add(Parameter.TASK_ID, taskId);
		params.add(Parameter.CHAPTER_ID, chapterId);
		params.add(Parameter.USER_ID, userId);
		
		cancelTaskService.execute(params);
    }
}
