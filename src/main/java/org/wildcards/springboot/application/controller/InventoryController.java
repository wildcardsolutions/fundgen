package org.wildcards.springboot.application.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.wildcards.springboot.application.constant.Parameter;
import org.wildcards.springboot.application.dto.InventoryDetails;
import org.wildcards.springboot.application.dto.MembershipCardDTO;
import org.wildcards.springboot.application.dto.MembershipCardListItem;
import org.wildcards.springboot.application.form.inventory.ListMembershipCardForm;
import org.wildcards.springboot.application.form.inventory.MembershipCardAssignmentForm;
import org.wildcards.springboot.application.form.inventory.MembershipCardDiscardForm;
import org.wildcards.springboot.application.form.inventory.MembershipCardRegistrationForm;
import org.wildcards.springboot.application.form.inventory.MembershipCardRequestForm;
import org.wildcards.springboot.domain.model.Chapter;
import org.wildcards.springboot.domain.model.MemberInformation;
import org.wildcards.springboot.domain.model.MembershipCard;
import org.wildcards.springboot.domain.model.ParameterMap;
import org.wildcards.springboot.domain.repository.MembershipCardRepository;
import org.wildcards.springboot.domain.service.Service;
import org.wildcards.springboot.infrastructure.rest.AbstractRestRequestHandler;

/**
 * 
 * @author jojo
 *
 */
@RestController
@RequestMapping("/api/inventory")
public class InventoryController extends AbstractRestRequestHandler {
	
	/**
	 * 
	 */
	@Autowired
	@Qualifier("allocateMembershipCardService")
	private Service<Long> allocateMembershipCards;
	
	/**
	 * 
	 */
	@Autowired
	@Qualifier("registerMembershipCardService")
	private Service<Long> registerMembershipCards;
	
	/**
	 * 
	 */
	@Autowired
	@Qualifier("requestMembershipCardService")
	private Service<Long> requestMembershipCards;
	
	/**
	 * 
	 */
	@Autowired
	@Qualifier("discardMembershipCardService")
	private Service<Long> discardMembershipCards;
	
	/**
	 * 
	 */
	@Autowired
	private MembershipCardRepository repository;
	
	
	/**
	 * 
	 * @param userId
	 * @param form
	 */
	//@Secured({"", ""})
	@RequestMapping(
			method=RequestMethod.POST,
			value="/register")
	@ResponseStatus(HttpStatus.OK)
	public void registerMembershipCards(
//			@RequestHeader(value="user-id") Long userId,
			@RequestBody MembershipCardRegistrationForm form) {
		
		long userId = 1;
		
		ParameterMap params = new ParameterMap();
		params.add(Parameter.CARD_TYPE, form.getCardType());
		params.add(Parameter.CARD_SERIES_START, form.getSeriesStart());
		params.add(Parameter.CARD_SERIES_END, form.getSeriesEnd());
		params.add(Parameter.USER_ID, userId);
		
		registerMembershipCards.execute(params);
		
	}
	
	/**
	 * 
	 * @param userId
	 * @param form
	 */
	@RequestMapping(
			method=RequestMethod.POST,
			value="/allocate")
	@ResponseStatus(HttpStatus.OK)
	public void allocateMembershipCardsToChapter(
			//@RequestHeader(value="user-id") Long userId,
			@RequestBody MembershipCardAssignmentForm form) {
		
		long userId = 1;
		
		ParameterMap params = new ParameterMap();
		params.add(Parameter.CHAPTER_ID, form.getChapter());
		params.add(Parameter.CARD_TYPE, form.getCardType());
		params.add(Parameter.CARD_SERIES_START, form.getSeriesStart());
		params.add(Parameter.CARD_SERIES_END, form.getSeriesEnd());
		params.add(Parameter.USER_ID, userId);
		
		allocateMembershipCards.execute(params);
		
	}
	
	/**
	 * 
	 * @param idNumber
	 * @param reason
	 */
	@RequestMapping(
			method=RequestMethod.POST,
			value="/discard")
	@ResponseStatus(HttpStatus.OK)
	public void discardMembershipCards(
			//@RequestHeader(value="chapter-id") Long chapterId,
			//@RequestHeader(value="user-id") Long userId,
			@RequestBody MembershipCardDiscardForm form) {
		
		long userId = 1;
		long chapterId = 1;
		
		ParameterMap params = new ParameterMap();
		params.add(Parameter.CHAPTER_ID, chapterId);
		params.add(Parameter.CARD_TYPE, form.getCardType());
		params.add(Parameter.CARD_SERIES_START, form.getSeriesStart());
		params.add(Parameter.CARD_SERIES_END, form.getSeriesEnd());
		params.add(Parameter.REASON, form.getReason());
		params.add(Parameter.USER_ID, userId);
		
		discardMembershipCards.execute(params);
	}
	
	
	@RequestMapping(
			method=RequestMethod.POST,
			value="/{cardType}")
	@ResponseStatus(HttpStatus.OK)
	public void getInventoryLevels(
			//@RequestHeader(value="chapter-id") Long chapterId,
			//@RequestHeader(value="user-id") Long userId,
			@PathVariable String cardType) {
		
		//membershipCardService.discardIdentificationCard(cardNumber, reason, userId);
		long userId = 1;
		long chapterId = 1;
		
		
	}
	
	@RequestMapping(
			method=RequestMethod.GET,
			value="/list/{pageIndex}/{pageSize}/{cardType}/{seriesStart}/{seriesEnd}")
	@ResponseStatus(HttpStatus.OK)
	public Object getMembershipCardList(
			//@RequestHeader(value="chapter-id") Long chapterId,
			//@RequestHeader(value="user-id") Long userId,
			@ModelAttribute ListMembershipCardForm form) {
		
		long userId = 1;
		long chapterId = 1;
		
		ParameterMap params = new ParameterMap();
		params.add(Parameter.CARD_TYPE, form.getCardType());
		params.add(Parameter.CARD_SERIES_START, form.getSeriesStart());
		params.add(Parameter.CARD_SERIES_END, form.getSeriesEnd());
//		params.add(Parameter.USER_ID, userId);
//		params.add(Parameter.CHAPTER_ID, chapterId);
	
		
		Page<MembershipCard> pagedMembershipCards = repository.getList(
				form.getCardType(), 
				form.getSeriesStart(), 
				form.getSeriesEnd(), 
				new PageRequest(form.getPageIndex()-1, form.getPageSize()));
		
		List<MembershipCardListItem> list = new ArrayList<MembershipCardListItem>();
		for (MembershipCard card : pagedMembershipCards.getContent()) {
			MembershipCardListItem item = new MembershipCardListItem();
			item.setActive(card.isActive());
			item.setDiscarded(null != card.getDateDiscarded());
			item.setCardNumber(card.getCardId());
			item.setCardId(card.getId());
			
			item.setCardType(card.getMembershipCardType().getName());
			Chapter chapter = card.getAssignedToChapter();
			if (null!=chapter) {
				item.setAssignedToChapter(chapter.getChapterName());
				item.setAssignedToChapterId(chapter.getId());
			}
			
			MemberInformation member = card.getAssignedToMember();
			if (null!=member) {
				item.setAssignedToMember(member.getFullName());
				item.setAssignedToMemberId(member.getId());
			}
			
			list.add(item);
		}
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalElements", pagedMembershipCards.getTotalElements());
		resultMap.put("totalPages", pagedMembershipCards.getTotalPages());
		resultMap.put("pageIndex", form.getPageIndex());
		resultMap.put("pageContent", list);
		return resultMap;
	}
	
	@RequestMapping(
			method=RequestMethod.GET,
			value="/{cardId}")
	@ResponseStatus(HttpStatus.OK)
	public MembershipCardDTO getMembershipCard(
			//@RequestHeader(value="chapter-id") Long chapterId,
			//@RequestHeader(value="user-id") Long userId,
			@PathVariable long cardId) {
		
		long userId = 1;
		long chapterId = 1;
		
		MembershipCard card = repository.getOne(cardId);
		MembershipCardDTO dto = new MembershipCardDTO();
		dto.setCardId(card.getId());
		dto.setCardNumber(card.getCardId());
		dto.setCardType(card.getMembershipCardType().getName());
		
		return dto;
	}
	
	@RequestMapping(
			method=RequestMethod.GET,
			value="/levels/{cardType}")
	@ResponseStatus(HttpStatus.OK)
	public InventoryDetails getInventoryLevels(
			//@RequestHeader(value="chapter-id") Long chapterId,
			//@RequestHeader(value="user-id") Long userId,
			@PathVariable long cardType) {
		
		long userId = 1;
		long chapterId = 1;
		
		InventoryDetails dto = new InventoryDetails();
		
		return dto;
	}
	
	
	@RequestMapping(
			method=RequestMethod.POST,
			value="/request")
	@ResponseStatus(HttpStatus.OK)
	public void requestMembershipCards(
			//@RequestHeader(value="chapter-id") Long chapterId,
			//@RequestHeader(value="user-id") Long userId,
			@RequestBody MembershipCardRequestForm form) {
		
		long userId = 1;
		long chapterId = 2;
		
		Map<String, Long> requestedCards = new HashMap<String, Long>();
		for(String key : form.getCards().keySet()) {
			System.out.println(key + "=" + form.getCards().get(key));
			requestedCards.put(key, form.getCards().get(key));
		}
		
		ParameterMap params = new ParameterMap();
		params.add(Parameter.CARDS_REQUESTED, requestedCards);
		params.add(Parameter.USER_ID, userId);
		params.add(Parameter.CHAPTER_ID, chapterId);
		
		requestMembershipCards.execute(params);
	}
	
}
