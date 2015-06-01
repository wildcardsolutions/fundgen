package org.wildcards.springboot.application.form.inventory;

public class ListMembershipCardForm extends AbstractMembershipCardInventoryForm {
	
	private int pageSize;
	
	private int pageIndex;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	
}
