package org.wildcards.springboot.domain.model.request;

import java.util.Date;

/**
 * 
 * @author jojo
 *
 */
public class RequestHistory {
	
	private String officer;
	
	private String chapter;
	
	private String action;
	
	private String remarks;
	
	private Date date;

	public String getOfficer() {
		return officer;
	}

	public void setOfficer(String officer) {
		this.officer = officer;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
