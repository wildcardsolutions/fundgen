package org.wildcards.springboot.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="task")
public class Task extends AbstractModel {
	
	@Column(name="task_type")
	private String taskType;
	
	@Column(name="task_status")
	private String taskStatus;
	
	@Column(name="request_id")
	private long requestId;
	
//	@Column(name="task_status")
//	private String taskStatus;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name= "task_id", nullable=false)
	private List<TaskHistory> taskHistory;
	

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public List<TaskHistory> getTaskHistory() {
		return taskHistory;
	}

	public void setTaskHistory(List<TaskHistory> taskHistory) {
		this.taskHistory = taskHistory;
	}

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
}
