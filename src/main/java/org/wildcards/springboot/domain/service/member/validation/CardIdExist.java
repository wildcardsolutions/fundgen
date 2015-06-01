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
public class CardIdExist extends AbstractValidator {

	/**
	 * 
	 */
	private static final String IS_CARD_ID_ALREADY_REGISTERED = 
			"{CALL is_card_id_already_registered(?, ?)}";
	
	/**
	 * 
	 * @param storedProcedureService
	 */
	public CardIdExist(StoredProcedureService storedProcedureService) {
		super(storedProcedureService);
	}

	/**
	 * 
	 */
	@Override
	protected void throwExceptionIfNotValidated(ParameterMap mapOfParameters) {
		System.out.println("CardIdExist");
		boolean registered = queryBoolean(
				IS_CARD_ID_ALREADY_REGISTERED,
				new Object[] {
						(Long) mapOfParameters.get(Parameter.CARD_TYPE),
						(Long) mapOfParameters.get(Parameter.CARD_NUMBER)});
		
		if (!registered) {
			throw new ValidationException("Card ID not yet registered.");
		}
	}
}
