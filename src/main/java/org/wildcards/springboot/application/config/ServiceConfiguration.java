package org.wildcards.springboot.application.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wildcards.springboot.domain.factory.TaskFactory;
import org.wildcards.springboot.domain.model.Chapter;
import org.wildcards.springboot.domain.model.MembershipCardRequest;
import org.wildcards.springboot.domain.model.MembershipCardType;
import org.wildcards.springboot.domain.model.Officer;
import org.wildcards.springboot.domain.model.Task;
import org.wildcards.springboot.domain.service.Service;
import org.wildcards.springboot.domain.service.member.RegisterMemberService;
import org.wildcards.springboot.domain.service.member.validation.CardIdAllocatedToChapter;
import org.wildcards.springboot.domain.service.member.validation.CardIdExist;
import org.wildcards.springboot.domain.service.member.validation.CardIdNotYetAssignedToMember;
import org.wildcards.springboot.domain.service.member.validation.CardIdNotYetDiscarded;
import org.wildcards.springboot.domain.service.membershipcard.AllocateMembershipCardService;
import org.wildcards.springboot.domain.service.membershipcard.DiscardMembershipCardService;
import org.wildcards.springboot.domain.service.membershipcard.InventoryLevelService;
import org.wildcards.springboot.domain.service.membershipcard.ListMembershipCardService;
import org.wildcards.springboot.domain.service.membershipcard.RegisterMembershipCardService;
import org.wildcards.springboot.domain.service.membershipcard.RequestMembershipCardService;
import org.wildcards.springboot.domain.service.membershipcard.validation.CardSeriesExist;
import org.wildcards.springboot.domain.service.membershipcard.validation.CardSeriesNotExist;
import org.wildcards.springboot.domain.service.membershipcard.validation.CardSeriesNotYetAllocatedToChapter;
import org.wildcards.springboot.domain.service.membershipcard.validation.CardSeriesNotYetAssignedToMember;
import org.wildcards.springboot.domain.service.membershipcard.validation.CardSeriesNotYetDiscarded;
import org.wildcards.springboot.domain.service.membershipcard.validation.CardSeriesAllocatedToChapter;
import org.wildcards.springboot.domain.service.task.CreateTaskService;
import org.wildcards.springboot.domain.service.validation.Validator;
import org.wildcards.springboot.infrastructure.persistence.StoredProcedureService;

/**
 * 
 * @author jojo
 *
 */
@Configuration
public class ServiceConfiguration {

	/**
	 * 
	 */
	@Autowired
	private StoredProcedureService storedProcedureService;
	
	
	@Autowired 
	private JpaRepository<Chapter, Long> chapterRepository;
	
	@Autowired 
	private JpaRepository<Officer, Long> officerRepository;
	
	@Autowired 
	private JpaRepository<MembershipCardRequest, Long> requestRepository;
	
	@Autowired 
	private JpaRepository<MembershipCardType, Long> cardTypeRepository;
	
	@Autowired 
	private JpaRepository<Task, Long> taskRespository;
	
	@Autowired 
	private TaskFactory taskFactory;
	
	/**
	 * 
	 * @return
	 */
	@Bean
	public Service<Long> registerMembershipCardService() {
		List<Validator> listOfValidators = new ArrayList<Validator>();
		listOfValidators.add(new CardSeriesNotExist(storedProcedureService));
		return new RegisterMembershipCardService(listOfValidators, storedProcedureService);
	}
	
	/**
	 * 
	 * @return
	 */
	@Bean
	public Service<List<?>> inventoryLevelService() {
		return new InventoryLevelService(storedProcedureService);
	}
	
	/**
	 * 
	 * @return
	 */
	@Bean
	public Service<Long> allocateMembershipCardService() {
		List<Validator> listOfValidators = new ArrayList<Validator>();
		listOfValidators.add(new CardSeriesExist(storedProcedureService));
		listOfValidators.add(new CardSeriesNotYetDiscarded(storedProcedureService));
		listOfValidators.add(new CardSeriesNotYetAllocatedToChapter(storedProcedureService));
		return new AllocateMembershipCardService(listOfValidators, storedProcedureService);
	}
	
	@Bean
	public Service<Long> discardMembershipCardService() {
		List<Validator> listOfValidators = new ArrayList<Validator>();
		listOfValidators.add(new CardSeriesExist(storedProcedureService));
		listOfValidators.add(new CardSeriesNotYetDiscarded(storedProcedureService));
		listOfValidators.add(new CardSeriesAllocatedToChapter(storedProcedureService));
		listOfValidators.add(new CardSeriesNotYetAssignedToMember(storedProcedureService));
		return new DiscardMembershipCardService(listOfValidators, storedProcedureService);
	}
	
	@Bean
	public Service<List<?>> listMembershipCardService() {
		List<Validator> listOfValidators = new ArrayList<Validator>();
		return new ListMembershipCardService(listOfValidators, storedProcedureService);
	}
	
	@Bean
	public Service<MembershipCardRequest> requestMembershipCardService() {
		List<Validator> listOfValidators = new ArrayList<Validator>();
		return new RequestMembershipCardService(
				listOfValidators, 
				requestRepository,
				chapterRepository,
				officerRepository,
				cardTypeRepository);
	}
	
	@Bean
	public Service<Task> createTaskService() {
		List<Validator> listOfValidators = new ArrayList<Validator>();
		return new CreateTaskService(
				listOfValidators, 
				taskFactory,
				taskRespository);
	}
	
	@Bean
	public Service<Long> registerMemberService() {
		List<Validator> listOfValidators = new ArrayList<Validator>();
		listOfValidators.add(new CardIdExist(storedProcedureService));
		listOfValidators.add(new CardIdNotYetDiscarded(storedProcedureService));
		listOfValidators.add(new CardIdAllocatedToChapter(storedProcedureService));
		listOfValidators.add(new CardIdNotYetAssignedToMember(storedProcedureService));
		return new RegisterMemberService(listOfValidators, storedProcedureService);
	}
	
	
}
