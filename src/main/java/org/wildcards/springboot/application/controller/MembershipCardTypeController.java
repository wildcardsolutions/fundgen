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
import org.wildcards.springboot.domain.model.MembershipCardType;
import org.wildcards.springboot.domain.repository.MembershipCardTypeRepository;

/**
 * 
 * @author jojo
 *
 */
@RestController
@RequestMapping("/api/membershipCardType")
public class MembershipCardTypeController {
	
	/**
	 * 
	 */
	@Autowired
	private MembershipCardTypeRepository repository;
	
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
	
	@RequestMapping(
			value = "/page/{page}/{size}",
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getPage(
    		@PathVariable(value="page") int page,
    		@PathVariable(value="size") int size) {
		Page<MembershipCardType> all = repository.findAll(new PageRequest(page-1, size));
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
		Page<MembershipCardType> matchingRoles = repository.searchFor(searchString, new PageRequest(page-1, size));
		
		result.put("totalElements", matchingRoles.getTotalElements());
		result.put("totalPages", matchingRoles.getTotalPages());
		result.put("pageIndex", page);
		result.put("pageContent", matchingRoles.getContent());
		
		return result;
    }
	
	
	@RequestMapping(
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<MembershipCardType> getMembershipCardTypes() {
		return repository.findAll();
	}
	
	@RequestMapping(
			method=RequestMethod.GET,
			value="/{id}")
	@ResponseStatus(HttpStatus.OK)
    public MembershipCardType getMembershipCardType(@PathVariable Long id) {
		return repository.findOne(id);
    }
	
	@RequestMapping(
			method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public MembershipCardType addMembershipCardType(@RequestBody MembershipCardType cardType) {
		Long userId = 1L;
		cardType.setCreatedBy(userId);
		cardType.setDateCreated(new Date());
		return repository.save(cardType);
    }
	
	@RequestMapping(
			value = "/{cardTypeId}",
			method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
    public MembershipCardType updateMembershipCardType(
    		@PathVariable(value="cardTypeId") Long cardTypeId,
    		@RequestBody MembershipCardType cardType) {
		Long userId = 1L;
		
//		cardType.setModifiedBy(userId);
//		cardType.setDateModified(new Date());
		
		MembershipCardType card = repository.getOne(cardTypeId);
		card.setModifiedBy(userId);
		card.setDateModified(new Date());
		card.setMaximumAge(cardType.getMaximumAge());
		card.setMinimumAge(cardType.getMinimumAge());
		card.setPrefix(cardType.getPrefix());
		card.setName(cardType.getName());
		
		return repository.save(card);
    }
	
	@RequestMapping(
			value = "/{cardTypeId}",
			method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
    public void deleteMembershipCardType(@PathVariable(value="cardTypeId") Long cardTypeId) {
		System.out.println("cardTypeId=" + cardTypeId);
		Long userId = 1L;
		repository.delete(cardTypeId, userId);
    }
	
	@RequestMapping(
			value = "/undelete/{cardTypeId}",
			method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public void undeleteMembershipCardType(@PathVariable(value="cardTypeId") Long cardTypeId) {
		System.out.println("cardTypeId=" + cardTypeId);
		Long userId = 1L;
		repository.undelete(cardTypeId, userId);
    }
}
