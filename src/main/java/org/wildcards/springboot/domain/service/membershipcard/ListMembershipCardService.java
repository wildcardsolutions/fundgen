package org.wildcards.springboot.domain.service.membershipcard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.wildcards.springboot.application.constant.Parameter;
import org.wildcards.springboot.domain.model.ParameterMap;
import org.wildcards.springboot.domain.repository.MembershipCardRepository;
import org.wildcards.springboot.domain.service.AbstractService;
import org.wildcards.springboot.domain.service.validation.Validator;
import org.wildcards.springboot.infrastructure.persistence.StoredProcedureService;

/**
 * 
 * @author jojo
 *
 */
public class ListMembershipCardService extends AbstractService<List<?>> {

	/**
	 * 
	 */
	private static final String LIST_MEMBERSHIP_CARDS = 
			"{CALL list_membership_cards_of_chapter(?, ?, ?, ?)}";
	
	
	@Autowired
	private MembershipCardRepository repository;
	
	/**
	 * 
	 */
	private StoredProcedureService storedProcedureService;
	
	/**
	 * 
	 * @param listOfValidators
	 * @param storedProcedureService
	 */
	public ListMembershipCardService(
			List<Validator> allocateMembershipCardValidators,
			StoredProcedureService storedProcedureService) {
		super(allocateMembershipCardValidators);
		this.storedProcedureService = storedProcedureService;
	}

	/**
	 * 
	 */
	@Override
	public List<?> doExecute(ParameterMap parameterMap) {
		return storedProcedureService.executeQuery(
				LIST_MEMBERSHIP_CARDS,
				new Object[] {
						parameterMap.get(Parameter.CHAPTER_ID), 
						parameterMap.get(Parameter.CARD_TYPE), 
						parameterMap.get(Parameter.CARD_SERIES_START), 
						parameterMap.get(Parameter.CARD_SERIES_END)});
	}

}
