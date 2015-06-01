package org.wildcards.springboot.domain.service.member.validation;

import org.wildcards.springboot.application.constant.Parameter;
import org.wildcards.springboot.domain.model.ParameterMap;
import org.wildcards.springboot.domain.service.validation.AbstractValidator;
import org.wildcards.springboot.domain.service.validation.ValidationException;
import org.wildcards.springboot.infrastructure.persistence.StoredProcedureService;

public class CardIdAllocatedToChapter extends AbstractValidator {

	/**
	 * 
	 */
	private static final String IS_CARD_ID_ALLOCATED_TO_CHAPTER = 
			"{CALL is_card_id_allocated_to_chapter(?, ?, ?)}";
	
	/**
	 * 
	 * @param storedProcedureService
	 */
	public CardIdAllocatedToChapter(StoredProcedureService storedProcedureService) {
		super(storedProcedureService);
	}

	/**
	 * 
	 */
	@Override
	protected void throwExceptionIfNotValidated(ParameterMap mapOfParameters) {
		boolean assigned = queryBoolean(
				IS_CARD_ID_ALLOCATED_TO_CHAPTER,
				new Object[] {
						(Long) mapOfParameters.get(Parameter.CARD_TYPE),
						(Long) mapOfParameters.get(Parameter.CARD_NUMBER),
						(Long) mapOfParameters.get(Parameter.CHAPTER_ID)});
		
		if (!assigned) {
			throw new ValidationException("Card ID not assigned to chapter.");
		}
	}

}
