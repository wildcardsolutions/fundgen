/**
 * 
 */
package org.wildcards.springboot.domain.service;

import org.wildcards.springboot.domain.model.ParameterMap;

/**
 * 
 * @author jojo
 *
 */
public interface Service<T> {
	
	/**
	 * 
	 */
	T execute(ParameterMap map);

}
