package org.wildcards.springboot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wildcards.springboot.domain.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
}
