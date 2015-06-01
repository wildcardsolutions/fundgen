/**
 * 
 */
package org.wildcards.springboot.domain.service;

import java.util.List;

import org.wildcards.springboot.domain.model.ParameterMap;
import org.wildcards.springboot.domain.service.validation.Validator;

/**
 * 
 * @author jojo
 *
 */
public abstract class AbstractService<T> implements Service<T> {

	/**
	 * 
	 */
	private List<Validator> listOfValidators;
	
	/**
	 * 
	 * @param rules
	 */
	public AbstractService(List<Validator> listOfValidators) {
		this.listOfValidators = listOfValidators;
	}

	/**
	 * 
	 */
	public void doValidate(ParameterMap map) {
		if (null!=listOfValidators) {
			for (Validator rule : listOfValidators) {
				rule.validate(map);
			}
		}
	}
	
	/**
	 * 
	 */
	@Override
	public T execute(ParameterMap map) {
		doValidate(map);
		return doExecute(map);
	}
	
	/**
	 * 
	 */
	public abstract T doExecute(ParameterMap map);

}
