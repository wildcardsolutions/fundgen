package org.wildcards.springboot.domain.service.membershipcard;

import java.util.List;

import org.wildcards.springboot.application.constant.Parameter;
import org.wildcards.springboot.domain.model.ParameterMap;
import org.wildcards.springboot.domain.service.AbstractService;
import org.wildcards.springboot.domain.service.validation.Validator;
import org.wildcards.springboot.infrastructure.persistence.StoredProcedureService;

/**
 * 
 * @author jojo
 *
 */
public class AllocateMembershipCardService extends AbstractService<Long> {

	/**
	 * 
	 */
	private static final String ALLOCATE_MEMBERSHIP_CARDS_TO_CHAPTER = 
			"{CALL allocate_membership_cards_to_chapter(?, ?, ?, ?, ?)}";
	
	/**
	 * 
	 */
	private StoredProcedureService storedProcedureService;
	
	/**
	 * 
	 * @param listOfValidators
	 * @param storedProcedureService
	 */
	public AllocateMembershipCardService(
			List<Validator> allocateMembershipCardValidators,
			StoredProcedureService storedProcedureService) {
		super(allocateMembershipCardValidators);
		this.storedProcedureService = storedProcedureService;
	}

	/**
	 * 
	 */
	@Override
	public Long doExecute(ParameterMap parameterMap) {
		return storedProcedureService.executeUpdate(
				ALLOCATE_MEMBERSHIP_CARDS_TO_CHAPTER,
				new Object[] {
						parameterMap.get(Parameter.CHAPTER_ID), 
						parameterMap.get(Parameter.CARD_TYPE), 
						parameterMap.get(Parameter.CARD_SERIES_START), 
						parameterMap.get(Parameter.CARD_SERIES_END), 
						parameterMap.get(Parameter.USER_ID)});
	}

}
