package org.wildcards.springboot.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.wildcards.springboot.domain.model.Chapter;
import org.wildcards.springboot.domain.model.MembershipCardType;

/**
 * 
 * @author jojo
 *
 */
public interface MembershipCardTypeRepository extends JpaRepository<MembershipCardType, Long> {

	@Query("SELECT COUNT(c.id) FROM MembershipCardType c")
	Long getCount();
	
	@Query("SELECT COUNT(c.id) FROM MembershipCardType c WHERE c.name LIKE CONCAT('%', :searchString, '%') OR c.prefix LIKE CONCAT('%', :searchString, '%')")
	Long getCount(@Param("searchString") String searchString);
	
	
	@Query("SELECT c FROM MembershipCardType c WHERE c.name LIKE CONCAT('%', :searchString, '%') OR c.prefix LIKE CONCAT('%', :searchString, '%')")
	Page<MembershipCardType> searchFor(@Param("searchString") String searchString, Pageable pageable);
	
	/**
	 * 
	 * @param cardTypeId
	 * @param deletedBy
	 */
	@Procedure(procedureName="delete_cardtype")
	void delete(Long cardTypeId, Long deletedBy);
	
	/**
	 * 
	 * @param cardTypeId
	 * @param deletedBy
	 */
	@Procedure(procedureName="undelete_cardtype")
	void undelete(Long cardTypeId, Long deletedBy);
}
