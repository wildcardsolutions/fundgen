package org.wildcards.springboot.domain.service.membershipcard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.wildcards.springboot.domain.model.ParameterMap;
import org.wildcards.springboot.domain.service.AbstractService;
import org.wildcards.springboot.infrastructure.persistence.StoredProcedureService;

/**
 * 
 * @author jojo
 *
 */
public class InventoryLevelService extends AbstractService<List<?>> {
	
	/**
	 * 
	 */
	private StoredProcedureService service;
	
	/**
	 * 
	 * @param service
	 */
	public InventoryLevelService(StoredProcedureService service) {
		super(null);
		this.service = service;
	}

	@Override
	public List<?> doExecute(ParameterMap map) {
		// TODO Auto-generated method stub
		return null;
	}
}
