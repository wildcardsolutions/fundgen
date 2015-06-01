package org.wildcards.springboot.infrastructure.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.wildcards.springboot.infrastructure.security.model.Role;

/**
 * 
 * @author jojo
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query("SELECT COUNT(c.roleId) FROM Role c")
	Long getCount();
	
	@Query("SELECT c FROM Role c WHERE c.roleName LIKE CONCAT('%', :searchString, '%')")
	Page<Role> searchFor(@Param("searchString") String searchString, Pageable pageable);
	
	@Procedure(procedureName="delete_role")
	void delete(Long id, Long deletedBy);
	
	@Procedure(procedureName="undelete_role")
	void undelete(Long id, Long undeletedBy);
}
