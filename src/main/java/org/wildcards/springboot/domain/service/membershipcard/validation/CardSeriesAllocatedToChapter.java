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
public class CardSeriesAllocatedToChapter extends AbstractValidator {

	/**
	 * 
	 */
	private static final String IS_CARD_SERIES_ALLOCATED_TO_CHAPTER = 
			"{CALL is_card_series_allocated_to_chapter(?, ?, ?, ?)}";
			
	/**
	 * 
	 * @param storedProcedureService
	 */
	public CardSeriesAllocatedToChapter(StoredProcedureService storedProcedureService) {
		super(storedProcedureService);
	}

	/**
	 * 
	 */
	@Override
	protected void throwExceptionIfNotValidated(ParameterMap mapOfParameters) {
		boolean owned = queryBoolean(
				IS_CARD_SERIES_ALLOCATED_TO_CHAPTER,
				new Object[] {
						(Long) mapOfParameters.get(Parameter.CHAPTER_ID),
						(Long) mapOfParameters.get(Parameter.CARD_TYPE),
						(Long) mapOfParameters.get(Parameter.CARD_SERIES_START),
						(Long) mapOfParameters.get(Parameter.CARD_SERIES_END)});
		
		if (!owned) {
			throw new ValidationException("Card(s) in the series not allocated to chapter.");
		}
		
	}

	

}
