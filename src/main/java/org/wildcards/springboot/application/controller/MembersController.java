package org.wildcards.springboot.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.wildcards.springboot.application.constant.Parameter;
import org.wildcards.springboot.application.form.membership.MembershipRegistrationForm;
import org.wildcards.springboot.domain.model.ParameterMap;
import org.wildcards.springboot.domain.repository.MemberInformationRepository;
import org.wildcards.springboot.domain.service.Service;
import org.wildcards.springboot.infrastructure.persistence.StoredProcedureService;
import org.wildcards.springboot.infrastructure.rest.AbstractRestRequestHandler;

@RestController
@RequestMapping("/api/members")
public class MembersController extends AbstractRestRequestHandler {

	/**
	 * 
	 */
	@Autowired
	private MemberInformationRepository repository;
	
	/**
	 * 
	 */
	@Autowired
	@Qualifier("registerMemberService")
	private Service<Long> registerMember;
	
	/**
	 * 
	 */
	@Autowired
	private StoredProcedureService storedProcedureService;
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(
			value = "/autocomplete/lastname/{searchTerm}",
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public Object autoCompleteLastname(@PathVariable(value="searchTerm") String searchTerm) {
		List<String> resultSet = (List<String>) storedProcedureService.executeQuery(
				"{CALL get_lastname_matching(?)}", new Object[] {searchTerm});
		return resultSet;
    }

	@SuppressWarnings("unchecked")
	@RequestMapping(
			value = "/autocomplete/firstname/{searchTerm}",
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<String> autoCompleteFirstname(@PathVariable(value="searchTerm") String searchTerm) {
		return (List<String>) storedProcedureService.executeQuery(
				"{CALL get_firstname_matching(?)}", new Object[] {searchTerm});
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(
			value = "/autocomplete/middlename/{searchTerm}",
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<String> autoCompleteMiddlename(@PathVariable(value="searchTerm") String searchTerm) {
		return (List<String>) storedProcedureService.executeQuery(
				"{CALL get_middlename_matching(?)}", new Object[] {searchTerm});
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(
			value = "/autocomplete/address/{searchTerm}",
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<String> autoCompleteAddress(@PathVariable(value="searchTerm") String searchTerm) {
		return (List<String>) storedProcedureService.executeQuery(
				"{CALL get_address_matching(?)}", new Object[] {searchTerm});
    }
	
	
	@RequestMapping(
			value = "/register",
			method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public Object register(
    		@RequestBody MembershipRegistrationForm memberInfo) {
		
		System.out.println(memberInfo.getCardNumber());
		System.out.println(memberInfo.getCardType());
		System.out.println(memberInfo.getEffectivityDate());
		System.out.println(memberInfo.getDeclarationPeriod());
		
		long userId = 1;
		long chapterId = 1;
		
		ParameterMap parameterMap = new ParameterMap();
		parameterMap.add(Parameter.CHAPTER_ID, chapterId);
		parameterMap.add(Parameter.USER_ID, userId);
		parameterMap.add(Parameter.CARD_TYPE, memberInfo.getCardType());
		parameterMap.add(Parameter.CARD_NUMBER, memberInfo.getCardNumber());
		
		registerMember.execute(parameterMap);
		
		return "";
    }
	
}
