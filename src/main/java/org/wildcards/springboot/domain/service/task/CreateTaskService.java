package org.wildcards.springboot.domain.service.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wildcards.springboot.application.constant.Parameter;
import org.wildcards.springboot.application.enumeration.TaskType;
import org.wildcards.springboot.domain.factory.TaskFactory;
import org.wildcards.springboot.domain.model.ParameterMap;
import org.wildcards.springboot.domain.model.Task;
import org.wildcards.springboot.domain.service.AbstractService;
import org.wildcards.springboot.domain.service.validation.Validator;

/**
 * 
 * @author jojo
 *
 */
public class CreateTaskService extends AbstractService<Task> {

	/**
	 * 
	 */
	private TaskFactory factory;
	
	/**
	 * 
	 */
	private JpaRepository<Task, Long> taskRepository;
	
	/**
	 * 
	 * @param listOfValidators
	 * @param taskRepository
	 */
	public CreateTaskService(
			List<Validator> listOfValidators,
			TaskFactory factory,
			JpaRepository<Task, Long> taskRepository) {
		super(listOfValidators);
		this.factory = factory;
		this.taskRepository = taskRepository;
	}

	@Override
	public Task doExecute(ParameterMap map) {
		TaskType taskType = (TaskType) map.get(Parameter.TASK_TYPE);
		long userId = (long) map.get(Parameter.USER_ID);
		long chapterId = (long) map.get(Parameter.CHAPTER_ID);
		long requestId = (long) map.get(Parameter.REQUEST_ID);
		
		return taskRepository.save(
				factory.createTask(
						taskType, 
						requestId, 
						userId, 
						chapterId));
	}

}
