package org.wildcards.springboot.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wildcards.springboot.domain.model.MembershipCard;

public interface MembershipCardRepository extends JpaRepository<MembershipCard, Long> {
	
	@Query("SELECT c FROM MembershipCard c WHERE c.cardType=:cardType AND c.idNumber>=:seriesStart AND c.idNumber<=:seriesEnd")
	Page<MembershipCard> getList(
			@Param("cardType") long cardType,
			@Param("seriesStart") long seriesStart,
			@Param("seriesEnd") long seriesEnd,
			Pageable pageable);
}
