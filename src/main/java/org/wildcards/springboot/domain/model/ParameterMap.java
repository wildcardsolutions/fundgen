/**
 * 
 */
package org.wildcards.springboot.domain.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author jojo
 *
 */
public class ParameterMap {

	/**
	 * 
	 */
	private Map<String, Object> mapOfParameters;
	
	/**
	 * 
	 */
	public ParameterMap() {
		mapOfParameters = new HashMap<String, Object>();
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void add(String key, Object value) {
		mapOfParameters.put(key, value);
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return mapOfParameters.get(key);
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public void remove(String key) {
		mapOfParameters.remove(key);
	}
	
	/**
	 * 
	 */
	public void clear() {
		mapOfParameters.clear();
	}
}
