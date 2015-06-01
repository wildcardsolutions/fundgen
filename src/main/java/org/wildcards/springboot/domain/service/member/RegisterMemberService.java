package org.wildcards.springboot.domain.service.member;

import java.util.List;

import org.wildcards.springboot.domain.model.ParameterMap;
import org.wildcards.springboot.domain.service.AbstractService;
import org.wildcards.springboot.domain.service.validation.Validator;
import org.wildcards.springboot.infrastructure.persistence.StoredProcedureService;

public class RegisterMemberService extends AbstractService<Long> {

	/**
	 * 
	 */
	private StoredProcedureService storedProcedureService;
	
	/**
	 * 
	 * @param listOfValidators
	 * @param storedProcedureService
	 */
	public RegisterMemberService(
			List<Validator> listOfValidators, 
			StoredProcedureService storedProcedureService) {
		super(listOfValidators);
		this.storedProcedureService = storedProcedureService;
	}

	@Override
	public Long doExecute(ParameterMap map) {
		// TODO Auto-generated method stub
		return null;
	}
 
}
