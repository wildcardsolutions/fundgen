package org.wildcards.springboot.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wildcards.springboot.domain.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query("SELECT COUNT(t.id) FROM Task t WHERE t.deleted=false")
	Long getCount();
	
	@Query("SELECT COUNT(t.id) FROM Task t WHERE t.deleted=false AND (t.taskStatus LIKE CONCAT('%', :searchString, '%') OR t.taskType LIKE CONCAT('%', :searchString, '%'))")
	Long getCount(@Param("searchString") String searchString);

	@Query("SELECT DISTINCT t FROM Task t INNER JOIN t.taskHistory th INNER JOIN th.chapter ch where ch.id = :chapterId AND t.deleted=false AND (t.taskStatus != 'CLOSED' AND t.taskStatus != 'DONE')" )
	Page<Task> getTasksByChapter(@Param("chapterId") long chapterId, Pageable pageable);

	@Query("SELECT DISTINCT t FROM Task t INNER JOIN t.taskHistory th INNER JOIN th.officer o where o.id = :officerId AND t.deleted=false AND (t.taskStatus != 'CLOSED' AND t.taskStatus != 'DONE')")
	Page<Task> getTasksByOfficer(@Param("officerId") long officerId, Pageable pageable);
	
	
	@Query("SELECT DISTINCT t FROM Task t INNER JOIN t.taskHistory th INNER JOIN th.chapter ch where ch.id = :chapterId AND t.deleted=false AND (t.taskStatus = 'CLOSED' OR t.taskStatus = 'DONE')" )
	Page<Task> getTaskHistoryByChapter(@Param("chapterId") long chapterId, Pageable pageable);
	
//	@Query("SELECT c FROM Task c WHERE c.taskHistory.chapter.id = :chapterId ")
//	Page<Task> findTasksByChapter(@Param("chapterId") long chapterId, Pageable pageable);
}
