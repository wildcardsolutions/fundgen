package org.wildcards.springboot.infrastructure.persistence;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.Transaction;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StoredProcedureService {

	/**
	 * 
	 */
	private static final Logger LOGGER = Logger.getLogger(StoredProcedureService.class);
	
	/**
	 * 
	 */
	@Autowired
	private EntityManagerFactory emf;

	/**
	 * 
	 * @param storedProcedure
	 * @param parameters
	 */
	public Long executeUpdate(String storedProcedure, Object[] parameters) {
		LOGGER.info("stored proc");
		Long rowsAffected = 0L;
		
		EntityManager em =  emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		try {
			Query q = em.createNativeQuery(storedProcedure);
			if (null!=parameters) {
				int i=1;
				for (Object parameter : parameters) {
					q.setParameter(i++, parameter);
				}
			}
			rowsAffected = (long) q.executeUpdate();
			t.commit();
		} catch (Exception e) {
			t.rollback();
			Throwable th = e.getCause();
			System.out.println(th.getMessage());
			System.out.println(th.getLocalizedMessage());
			throw e;
		}
	  return rowsAffected;
	}

	/**
	 * 
	 * @param storedProcedure
	 * @param parameters
	 * @return
	 */
	public List<?> executeQuery(String storedProcedure, Object[] parameters) {
		EntityManager em =  emf.createEntityManager();
		List<?> resultSet;
		
		try {
			Query q = em.createNativeQuery(storedProcedure);
			if (null!=parameters) {
				int i=1;
				for (Object parameter : parameters) {
					q.setParameter(i++, parameter);
				}
			}
			resultSet = q.getResultList();
		} catch (Exception e) {
			Throwable th = e.getCause();
			System.out.println(th.getMessage());
			System.out.println(th.getLocalizedMessage());
			throw e;
		}
		
		return resultSet;
	}
}
