package org.wildcards.springboot.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name="chapter")
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
			name="deleteChapter", 
			procedureName="delete_chapter", 
			parameters={
					@StoredProcedureParameter(type = Long.class),
					@StoredProcedureParameter(type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(
			name="undeleteChapter", 
			procedureName="undelete_chapter", 
			parameters={
					@StoredProcedureParameter(type = Long.class),
					@StoredProcedureParameter(type = Long.class)
			}
	)
})
public class Chapter extends AbstractModel {
	
	@Column(nullable = false)
	private String chapterName;
	
	@Column(nullable = false)
	private String chapterLocation;

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getChapterLocation() {
		return chapterLocation;
	}

	public void setChapterLocation(String chapterLocation) {
		this.chapterLocation = chapterLocation;
	}
	
}
