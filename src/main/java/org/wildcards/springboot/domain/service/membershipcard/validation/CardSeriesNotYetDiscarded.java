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
public class CardSeriesNotYetDiscarded extends AbstractValidator {

	/**
	 * 
	 */
	private static final String IS_CARD_SERIES_ALREADY_DISCARDED = 
			"{CALL is_card_series_already_discarded(?, ?, ?)}";
	
	/**
	 * 
	 * @param storedProcedureService
	 */
	public CardSeriesNotYetDiscarded(StoredProcedureService storedProcedureService) {
		super(storedProcedureService);
	}

	/**
	 * 
	 */
	@Override
	protected void throwExceptionIfNotValidated(ParameterMap mapOfParameters) {
		boolean discarded = queryBoolean(
				IS_CARD_SERIES_ALREADY_DISCARDED,
				new Object[] {
						(Long) mapOfParameters.get(Parameter.CARD_TYPE),
						(Long) mapOfParameters.get(Parameter.CARD_SERIES_START),
						(Long) mapOfParameters.get(Parameter.CARD_SERIES_END)});
		
		if (discarded) {
			throw new ValidationException("Card(s) in the series already discarded.");
		}
	}

}
