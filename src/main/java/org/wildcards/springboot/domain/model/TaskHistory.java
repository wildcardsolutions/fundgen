package org.wildcards.springboot.domain.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.wildcards.springboot.application.enumeration.RequestMembershipCardStatus;

@Entity
@Table(name="task_history")
public class TaskHistory extends AbstractModel {


	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "task_history_map_chapter", 
			joinColumns = { @JoinColumn(name = "taskId") }, 
			inverseJoinColumns = { @JoinColumn(name = "chapterId") })
	private Chapter chapter;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "task_history_map_officer", 
			joinColumns = { @JoinColumn(name = "taskId") }, 
			inverseJoinColumns = { @JoinColumn(name = "officerId") })
	private Officer officer;
	
	@Column
	private String status;

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public Officer getOfficer() {
		return officer;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
