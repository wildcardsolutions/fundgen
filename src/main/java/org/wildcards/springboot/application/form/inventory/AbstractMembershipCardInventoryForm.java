package org.wildcards.springboot.application.form.inventory;

/**
 * 
 * @author jojo
 *
 */
public abstract class AbstractMembershipCardInventoryForm {

	/**
	 * 
	 */
	protected Long cardType;
	
	/**
	 * 
	 */
	protected Long seriesStart;
	
	/**
	 * 
	 */
	protected Long seriesEnd;

	public Long getCardType() {
		return cardType;
	}

	public void setCardType(Long cardType) {
		this.cardType = cardType;
	}

	public Long getSeriesStart() {
		return seriesStart;
	}

	public void setSeriesStart(Long seriesStart) {
		this.seriesStart = seriesStart;
	}

	public Long getSeriesEnd() {
		return seriesEnd;
	}

	public void setSeriesEnd(Long seriesEnd) {
		this.seriesEnd = seriesEnd;
	}

}