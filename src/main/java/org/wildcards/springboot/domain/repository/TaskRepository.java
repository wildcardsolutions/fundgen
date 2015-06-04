package org.wildcards.springboot.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wildcards.springboot.domain.model.Chapter;
import org.wildcards.springboot.domain.model.Officer;
import org.wildcards.springboot.domain.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query("SELECT COUNT(c.id) FROM Task c")
	Long getCount();

	@Query("SELECT t FROM Task t INNER JOIN t.taskHistory th INNER JOIN th.chapter ch where ch.id = :chapterId AND (t.taskStatus != 'CLOSED' OR t.taskStatus != 'DONE')" )
	Page<Task> getTasksByChapter(@Param("chapterId") long chapterId, Pageable pageable);

	@Query("SELECT t FROM Task t INNER JOIN t.taskHistory th INNER JOIN th.officer o where o.id = :officerId AND (t.taskStatus != 'CLOSED' OR t.taskStatus != 'DONE')")
	Page<Task> getTasksByOfficer(@Param("officerId") long officerId, Pageable pageable);
	
	
	@Query("SELECT t FROM Task t INNER JOIN t.taskHistory th INNER JOIN th.chapter ch where ch.id = :chapterId AND (t.taskStatus = 'CLOSED' OR t.taskStatus = 'DONE')" )
	Page<Task> getTaskHistoryByChapter(@Param("chapterId") long chapterId, Pageable pageable);
	
//	@Query("SELECT c FROM Task c WHERE c.taskHistory.chapter.id = :chapterId ")
//	Page<Task> findTasksByChapter(@Param("chapterId") long chapterId, Pageable pageable);
}
