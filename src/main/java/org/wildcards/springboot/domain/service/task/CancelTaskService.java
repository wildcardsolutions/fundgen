package org.wildcards.springboot.domain.service.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wildcards.springboot.application.constant.Parameter;
import org.wildcards.springboot.application.enumeration.RequestMembershipCardStatus;
import org.wildcards.springboot.domain.model.Chapter;
import org.wildcards.springboot.domain.model.Officer;
import org.wildcards.springboot.domain.model.ParameterMap;
import org.wildcards.springboot.domain.model.Task;
import org.wildcards.springboot.domain.model.TaskHistory;
import org.wildcards.springboot.domain.repository.TaskRepository;
import org.wildcards.springboot.domain.service.AbstractService;
import org.wildcards.springboot.domain.service.validation.Validator;

/**
 * 
 * @author jojo
 *
 */
public class CancelTaskService extends AbstractService<Task> {

	/**
	 * 
	 */
	private JpaRepository<Task, Long> taskRepository;
	
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
	
	
	public CancelTaskService(
			List<Validator> listOfValidators,
			JpaRepository<Task, Long> taskRepository,
			JpaRepository<Chapter, Long> chapterRepository,
			JpaRepository<Officer, Long> officerRepository) {
		super(listOfValidators);
		
		this.taskRepository = taskRepository;
		this.chapterRepository = chapterRepository;
		this.officerRepository = officerRepository;
	}

	@Override
	public Task doExecute(ParameterMap map) {
		Long taskId = (Long) map.get(Parameter.TASK_ID);
		Long chapterId = (Long) map.get(Parameter.CHAPTER_ID);
		Long officerId = (Long) map.get(Parameter.USER_ID);
		
		Task task = taskRepository.getOne(taskId);
		task.setDateDeleted(new Date());
		task.setDeletedBy(officerId);
		task.setTaskStatus(RequestMembershipCardStatus.CLOSED.getDescription());
		
		TaskHistory history = new TaskHistory();
		history.setDateCreated(new Date());
		history.setCreatedBy(officerId);
		history.setStatus(RequestMembershipCardStatus.CLOSED.getDescription());
		history.setChapter(chapterRepository.getOne(chapterId));
		history.setOfficer(officerRepository.getOne(officerId));
		
		List<TaskHistory> list = task.getTaskHistory();
		list.add(history);

		return taskRepository.save(task);
	}

}
