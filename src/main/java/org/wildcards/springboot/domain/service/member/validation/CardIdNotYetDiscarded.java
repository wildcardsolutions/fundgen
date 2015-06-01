/**
 * 
 */
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
public class CardIdNotYetDiscarded extends AbstractValidator {

	/**
	 * 
	 */
	private static final String IS_CARD_ID_ALREADY_DISCARDED = 
			"{CALL is_card_id_already_discarded(?, ?)}";
	
	/**
	 * 
	 * @param storedProcedureService
	 */
	public CardIdNotYetDiscarded(StoredProcedureService storedProcedureService) {
		super(storedProcedureService);
	}

	/**
	 * 
	 */
	@Override
	protected void throwExceptionIfNotValidated(ParameterMap mapOfParameters) {
		boolean discarded = queryBoolean(
				IS_CARD_ID_ALREADY_DISCARDED,
				new Object[] {
						(Long) mapOfParameters.get(Parameter.CARD_TYPE),
						(Long) mapOfParameters.get(Parameter.CARD_NUMBER)});
		
		if (discarded) {
			throw new ValidationException("Card ID already discarded.");
		}
	}
	
}
