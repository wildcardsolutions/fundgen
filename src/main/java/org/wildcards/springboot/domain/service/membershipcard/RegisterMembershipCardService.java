package org.wildcards.springboot.domain.service.membershipcard;

import java.util.List;

import org.wildcards.springboot.application.constant.Parameter;
import org.wildcards.springboot.domain.model.ParameterMap;
import org.wildcards.springboot.domain.service.AbstractService;
import org.wildcards.springboot.domain.service.validation.Validator;
import org.wildcards.springboot.infrastructure.persistence.StoredProcedureService;

/**
 * 
 * @author jojo
 *
 */
public class RegisterMembershipCardService extends AbstractService<Long> {

	/**
	 * 
	 */
	private static final String REGISTER_MEMBERSHIP_CARDS = 
			"{CALL register_membership_cards(?, ?, ?, ?)}";
	
	
	/**
	 * 
	 */
	private StoredProcedureService storedProcedureService;
	
	/**
	 * 
	 * @param listOfValidators
	 */

	public RegisterMembershipCardService(
			List<Validator> registerMembershipCardValidators,
			StoredProcedureService storedProcedureService) {
		super(registerMembershipCardValidators);
		this.storedProcedureService = storedProcedureService;
	}

	/**
	 * 
	 */
	@Override
	public Long doExecute(ParameterMap parameterMap) {
		return storedProcedureService.executeUpdate(
				REGISTER_MEMBERSHIP_CARDS,
				new Object[] {
						parameterMap.get(Parameter.CARD_TYPE), 
						parameterMap.get(Parameter.CARD_SERIES_START), 
						parameterMap.get(Parameter.CARD_SERIES_END), 
						parameterMap.get(Parameter.USER_ID)});
	}

}
