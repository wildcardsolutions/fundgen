package org.wildcards.springboot.domain.service.member.validation;

import org.wildcards.springboot.application.constant.Parameter;
import org.wildcards.springboot.domain.model.ParameterMap;
import org.wildcards.springboot.domain.service.validation.AbstractValidator;
import org.wildcards.springboot.domain.service.validation.ValidationException;
import org.wildcards.springboot.infrastructure.persistence.StoredProcedureService;

/**
 * 
 * @author jojo
 *
 */
public class CardIdNotYetAssignedToMember extends AbstractValidator {

	/**
	 * 
	 */
	private static final String IS_CARD_ID_ALREADY_ASSIGNED_TO_MEMBER = 
			"{CALL is_card_id_already_assigned_to_member(?, ?)}";
	
	/**
	 * 
	 * @param storedProcedureService
	 */
	public CardIdNotYetAssignedToMember(StoredProcedureService storedProcedureService) {
		super(storedProcedureService);
	}

	/**
	 * 
	 */
	@Override
	protected void throwExceptionIfNotValidated(ParameterMap mapOfParameters) {
		boolean assigned = queryBoolean(
				IS_CARD_ID_ALREADY_ASSIGNED_TO_MEMBER,
				new Object[] {
						(Long) mapOfParameters.get(Parameter.CARD_TYPE),
						(Long) mapOfParameters.get(Parameter.CARD_NUMBER)});
		
		if (assigned) {
			throw new ValidationException("Card ID already assigned to a member.");
		}
	}

}
