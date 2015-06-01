package org.wildcards.springboot.application.form.inventory;

import java.util.Map;

public class MembershipCardRequestForm {
	
	private Map<String, Long> cards;

	public Map<String, Long> getCards() {
		return cards;
	}

	public void setCards(Map<String, Long> cards) {
		this.cards = cards;
	}
	
	
	
}
