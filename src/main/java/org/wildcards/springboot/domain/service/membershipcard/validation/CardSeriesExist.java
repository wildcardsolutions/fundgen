package org.wildcards.springboot.domain.service.membershipcard.validation;

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
public class CardSeriesExist extends AbstractValidator {

	/**
	 * 
	 */
	private static final String IS_CARD_SERIES_ALREADY_REGISTERED = 
			"{CALL is_card_series_already_registered(?, ?, ?)}";
	
	/**
	 * 
	 * @param storedProcedureService
	 */
	public CardSeriesExist(StoredProcedureService storedProcedureService) {
		super(storedProcedureService);
	}

	/**
	 * 
	 */
	@Override
	protected void throwExceptionIfNotValidated(ParameterMap mapOfParameters) {
		System.out.println("CardSeriesExist");
		boolean registered = queryBoolean(
				IS_CARD_SERIES_ALREADY_REGISTERED,
				new Object[] {
						(Long) mapOfParameters.get(Parameter.CARD_TYPE),
						(Long) mapOfParameters.get(Parameter.CARD_SERIES_START),
						(Long) mapOfParameters.get(Parameter.CARD_SERIES_END)});
		
		if (!registered) {
			throw new ValidationException("Card(s) in the series not yet registered.");
		}
	}
}
