package org.wildcards.springboot.domain.service.validation;

import java.util.List;

import org.wildcards.springboot.domain.model.ParameterMap;
import org.wildcards.springboot.domain.service.validation.Validator;
import org.wildcards.springboot.infrastructure.persistence.StoredProcedureService;

/**
 * 
 * @author jojo
 *
 */
public abstract class AbstractValidator implements Validator {

	/**
	 * 
	 */
	protected StoredProcedureService storedProcedureService;

	/**
	 * 
	 * @param storedProcedureService
	 */
	public AbstractValidator(StoredProcedureService storedProcedureService) {
		this.storedProcedureService = storedProcedureService;
	}

	/**
	 * 
	 */
	@Override
	public void validate(ParameterMap mapOfParameters) {
		throwExceptionIfNotValidated(mapOfParameters);
	}

	/**
	 * 
	 * @param mapOfParameters
	 * @return
	 */
	protected abstract void throwExceptionIfNotValidated(ParameterMap mapOfParameters);
	
	/**
	 * 
	 * @param query
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected boolean queryBoolean(String query, Object[] parameters) {
		List<String> resultSet = (List<String>) storedProcedureService.executeQuery(query,parameters);
		return new Boolean(resultSet.get(0));
	}

}