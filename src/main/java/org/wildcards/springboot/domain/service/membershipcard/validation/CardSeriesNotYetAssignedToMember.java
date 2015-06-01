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
public class CardSeriesNotYetAssignedToMember extends AbstractValidator {

	/**
	 * 
	 */
	private static final String IS_CARD_SERIES_ALREADY_ASSIGNED = 
			"{CALL is_card_series_already_assigned_to_member(?, ?, ?)}";
	
	/**
	 * 
	 * @param storedProcedureService
	 */
	public CardSeriesNotYetAssignedToMember(StoredProcedureService storedProcedureService) {
		super(storedProcedureService);
	}

	/**
	 * 
	 */
	@Override
	protected void throwExceptionIfNotValidated(ParameterMap mapOfParameters) {
		boolean assigned = queryBoolean(
				IS_CARD_SERIES_ALREADY_ASSIGNED,
				new Object[] {
						(Long) mapOfParameters.get(Parameter.CARD_TYPE),
						(Long) mapOfParameters.get(Parameter.CARD_SERIES_START),
						(Long) mapOfParameters.get(Parameter.CARD_SERIES_END)});
		
		if (assigned) {
			throw new ValidationException("Card(s) in the series already assigned to member(s).");
		}
	}

}
