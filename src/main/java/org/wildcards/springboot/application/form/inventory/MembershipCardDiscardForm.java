package org.wildcards.springboot.application.form.inventory;

/**
 * 
 * @author jojo
 *
 */
public class MembershipCardDiscardForm extends AbstractMembershipCardInventoryForm {
	
	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
