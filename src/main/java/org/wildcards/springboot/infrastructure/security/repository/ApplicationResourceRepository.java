package org.wildcards.springboot.infrastructure.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wildcards.springboot.infrastructure.security.model.ApplicationResource;

/**
 * 
 * @author jojo
 *
 */
public interface ApplicationResourceRepository extends JpaRepository<ApplicationResource, Long> {

	@Query("SELECT r FROM ApplicationResource r WHERE LOWER(r.resource) = LOWER(:resourceName)")
	ApplicationResource findResourceByName(@Param("resourceName") String resourceName);

}
