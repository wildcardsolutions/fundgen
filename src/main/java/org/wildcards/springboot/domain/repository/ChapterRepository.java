/**
 * 
 */
package org.wildcards.springboot.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.wildcards.springboot.domain.model.Chapter;

/**
 * 
 * @author jojo
 *
 */
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
	
	@Query("SELECT COUNT(c.id) FROM Chapter c")
	Long getCount();
	
	@Query("SELECT COUNT(c.id) FROM Chapter c WHERE c.chapterName LIKE CONCAT('%', :searchString, '%') OR c.chapterLocation LIKE CONCAT('%', :searchString, '%')")
	Long getCount(@Param("searchString") String searchString);
	
	@Query("SELECT c FROM Chapter c WHERE c.chapterName LIKE CONCAT('%', :searchString, '%') OR c.chapterLocation LIKE CONCAT('%', :searchString, '%')")
	Page<Chapter> searchFor(@Param("searchString") String searchString, Pageable pageable);
	
	@Procedure(procedureName="delete_chapter")
	void delete(Long systemId, Long deletedBy);
	
	@Procedure(procedureName="undelete_chapter")
	void undelete(Long systemId, Long deletedBy);
}
