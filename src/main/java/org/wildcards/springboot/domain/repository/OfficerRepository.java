package org.wildcards.springboot.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wildcards.springboot.domain.model.Officer;
import org.wildcards.springboot.domain.model.SystemSetting;

/**
 * 
 * @author jojo
 *
 */
public interface OfficerRepository extends JpaRepository<Officer, Long>  {

	@Query("SELECT COUNT(c.id) FROM Officer c")
	Long getCount();
	
	@Query("SELECT c FROM Officer c WHERE c.firstname LIKE CONCAT('%', :searchString, '%') OR  c.lastname LIKE CONCAT('%', :searchString, '%')")
	Page<Officer> searchFor(@Param("searchString") String searchString, Pageable pageable);
	
}
