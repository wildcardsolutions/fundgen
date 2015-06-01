package org.wildcards.springboot.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.wildcards.springboot.domain.model.SystemSetting;

/**
 * 
 * @author jojo
 *
 */
public interface SettingsRepository extends JpaRepository<SystemSetting, Long> {

	@Query("SELECT COUNT(c.id) FROM SystemSetting c")
	Long getCount();
	
	@Query("SELECT c FROM SystemSetting c WHERE c.name LIKE CONCAT('%', :searchString, '%')")
	Page<SystemSetting> searchFor(@Param("searchString") String searchString, Pageable pageable);
	
	@Query("SELECT c FROM SystemSetting c WHERE c.name=:name")
	SystemSetting getByName(@Param("name") String name);
	
	@Procedure(procedureName="delete_setting")
	void delete(Long systemId, Long deletedBy);
	
	@Procedure(procedureName="undelete_setting")
	void undelete(Long systemId, Long deletedBy);
}
