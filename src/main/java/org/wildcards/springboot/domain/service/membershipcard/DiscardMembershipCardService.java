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
public class DiscardMembershipCardService extends AbstractService<Long> {

	/**
	 * 
	 */
	private static final String DISCARD_MEMBERSHIP_CARDS = 
			"{CALL discard_membership_cards(?, ?, ?, ?, ?, ?)}";
	
	/**
	 * 
	 */
	private StoredProcedureService storedProcedureService;
	
	/**
	 * 
	 * @param listOfValidators
	 */
	public DiscardMembershipCardService(
			List<Validator> listOfValidators, 
			StoredProcedureService storedProcedureService) {
		super(listOfValidators);
		this.storedProcedureService = storedProcedureService;
	}

	@Override
	public Long doExecute(ParameterMap parameterMap) {
		return storedProcedureService.executeUpdate(
				DISCARD_MEMBERSHIP_CARDS,
				new Object[] {
						parameterMap.get(Parameter.CHAPTER_ID), 
						parameterMap.get(Parameter.CARD_TYPE), 
						parameterMap.get(Parameter.CARD_SERIES_START), 
						parameterMap.get(Parameter.CARD_SERIES_END), 
						parameterMap.get(Parameter.REASON), 
						parameterMap.get(Parameter.USER_ID)});
		
	}

}
