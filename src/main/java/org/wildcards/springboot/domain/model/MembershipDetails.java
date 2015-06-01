package org.wildcards.springboot.domain.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class MembershipDetails {
	
	private Long declarationPeriod;
	private Long cardType;
	private Long cardId;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date effectivityDate;
	
	public Long getDeclarationPeriod() {
		return declarationPeriod;
	}
	public void setDeclarationPeriod(Long declarationPeriod) {
		this.declarationPeriod = declarationPeriod;
	}
	public Long getCardType() {
		return cardType;
	}
	public void setCardType(Long cardType) {
		this.cardType = cardType;
	}
	public Long getCardId() {
		return cardId;
	}
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}
	public Date getEffectivityDate() {
		return effectivityDate;
	}
	public void setEffectivityDate(Date effectivityDate) {
		this.effectivityDate = effectivityDate;
	}
	
	
}
