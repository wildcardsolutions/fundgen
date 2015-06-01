package org.wildcards.springboot.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.wildcards.springboot.domain.model.MemberInformation;

/**
 * 
 * @author jojo
 *
 */
public interface MemberInformationRepository extends JpaRepository<MemberInformation, Long> {

	/**
	 * 
	 * @param lastname
	 * @return
	 */
	@Procedure(procedureName="get_lastnames_matching")
	List<String> getLastnamesMatching(String lastname);
	
	/**
	 * 
	 * @param lastname
	 * @return
	 */
	@Procedure(procedureName="get_firstnames_matching")
	List<String> getFirstnamesMatching(String lastname);
	
	
}
