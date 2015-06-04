package org.wildcards.springboot.domain.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.wildcards.springboot.application.enumeration.RequestMembershipCardStatus;
import org.wildcards.springboot.application.enumeration.TaskType;
import org.wildcards.springboot.domain.model.Chapter;
import org.wildcards.springboot.domain.model.Officer;
import org.wildcards.springboot.domain.model.Task;
import org.wildcards.springboot.domain.model.TaskHistory;




/**
 * 
 * @author jojo
 *
 */
@Component
public class TaskFactory {
	
	/**
	 * 
	 */
	@Autowired 
	private JpaRepository<Chapter, Long> chapterRepository;
	
	/**
	 * 
	 */
	@Autowired 
	private JpaRepository<Officer, Long> officerRepository;
	

	public Task createTask(TaskType taskType, long requestId, long officerId, long chapterId) {
		Task task = new Task();
		task.setDateCreated(new Date());
		task.setCreatedBy(officerId);
		task.setTaskType(taskType.getDescription());
		task.setRequestId(requestId);
		
		TaskHistory history = new TaskHistory();
		history.setDateCreated(new Date());
		history.setCreatedBy(officerId);
		history.setStatus(RequestMembershipCardStatus.NEW.getDescription());
		history.setChapter(chapterRepository.getOne(chapterId));
		history.setOfficer(officerRepository.getOne(officerId));
		
		List<TaskHistory> taskHistoryList = new ArrayList<TaskHistory>();
		taskHistoryList.add(history);
		
		task.setTaskHistory(taskHistoryList);
		
		return task;
	}
}
