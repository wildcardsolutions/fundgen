package org.wildcards.springboot.domain.service.validation;

import org.wildcards.springboot.domain.model.ParameterMap;

/**
 * 
 * @author jojo
 *
 */
public interface Validator {
	
	/**
	 * @param mapOfParameters 
	 * 
	 */
	void validate(ParameterMap mapOfParameters);

}
