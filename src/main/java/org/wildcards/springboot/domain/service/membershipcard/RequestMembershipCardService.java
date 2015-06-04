package org.wildcards.springboot.domain.service.membershipcard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wildcards.springboot.application.constant.Parameter;
import org.wildcards.springboot.domain.model.Chapter;
import org.wildcards.springboot.domain.model.MembershipCardRequest;
import org.wildcards.springboot.domain.model.MembershipCardRequestItem;
import org.wildcards.springboot.domain.model.MembershipCardType;
import org.wildcards.springboot.domain.model.Officer;
import org.wildcards.springboot.domain.model.ParameterMap;
import org.wildcards.springboot.domain.repository.MembershipCardTypeRepository;
import org.wildcards.springboot.domain.service.AbstractService;
import org.wildcards.springboot.domain.service.validation.Validator;
import org.wildcards.springboot.infrastructure.persistence.StoredProcedureService;


/**
 * 
 * @author jojo
 *
 */
public class RequestMembershipCardService extends AbstractService<Long> {

	/**
	 * 
	 */
	private static final String REQUEST_MEMBERSHIP_CARDS = null;
	
	/**
	 * 
	 */
	private JpaRepository<MembershipCardRequest, Long> requestRepository;
	
	/**
	 * 
	 */
	private JpaRepository<Chapter, Long> chapterRepository;
	
	/**
	 * 
	 */
	private JpaRepository<Officer, Long> officerRepository;
	
	/**
	 * 
	 */
	private JpaRepository<MembershipCardType, Long> cardTypeRepository;
	
	/**
	 * 
	 * @param registerMembershipCardValidators
	 * @param storedProcedureService
	 */
	public RequestMembershipCardService(
			List<Validator> validators,
			JpaRepository<MembershipCardRequest, Long> requestRepository,
			JpaRepository<Chapter, Long> chapterRepository,
			JpaRepository<Officer, Long> officerRepository,
			JpaRepository<MembershipCardType, Long> cardTypeRepository) {
		super(validators);
		this.requestRepository = requestRepository;
		this.chapterRepository = chapterRepository;
		this.officerRepository = officerRepository;
		this.cardTypeRepository = cardTypeRepository;
	}

	/**
	 * 
	 */
	@Transactional
	@Override
	public Long doExecute(ParameterMap parameterMap) {
		@SuppressWarnings({ "unchecked" })
		Map<String, Long> requestedCards = (Map<String, Long>) parameterMap.get(Parameter.CARDS_REQUESTED);
		Long chapterId = (Long) parameterMap.get(Parameter.CHAPTER_ID);
		Long userId = (Long) parameterMap.get(Parameter.USER_ID);
		
		Chapter c = chapterRepository.getOne(chapterId);
		System.out.println(c.getChapterName());
		
		MembershipCardRequest request = new MembershipCardRequest();
		request.setDateCreated(new Date());
		request.setCreatedBy(userId);
		request.setChapter(chapterRepository.findOne(chapterId));
		request.setOfficer(officerRepository.findOne(userId));
		request.setRequestedCards(generateRequestedCards(requestedCards));
		
		requestRepository.save(request);
		
		return null;
	}

	/**
	 * 
	 * @param requestedCards
	 * @return
	 */
	private List<MembershipCardRequestItem> generateRequestedCards(Map<String, Long> requestedCards) {
		List<MembershipCardRequestItem> requestedCardList = new ArrayList<MembershipCardRequestItem>();
		for (String key : requestedCards.keySet()) {
			MembershipCardRequestItem item = new MembershipCardRequestItem();
			item.setCardTypeRequested(
					((MembershipCardTypeRepository)cardTypeRepository).getByName(key));
			item.setItemsRequested(requestedCards.get(key));
			requestedCardList.add(item);
		}
		return requestedCardList;
	}
	
	
	

}
