package org.wildcards.springboot.application.form.inventory;

/**
 * 
 * @author jojo
 *
 */
public class MembershipCardAssignmentForm extends AbstractMembershipCardInventoryForm {

	/**
	 * 
	 */
	private Long chapter;

	/**
	 * 
	 * @return
	 */
	public Long getChapter() {
		return chapter;
	}

	/**
	 * 
	 * @param chapter
	 */
	public void setChapter(Long chapter) {
		this.chapter = chapter;
	}

}
