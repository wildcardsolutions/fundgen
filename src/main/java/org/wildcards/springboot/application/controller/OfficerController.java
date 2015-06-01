package org.wildcards.springboot.application.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.wildcards.springboot.application.dto.OfficerDTO;
import org.wildcards.springboot.domain.model.Chapter;
import org.wildcards.springboot.domain.model.Officer;
import org.wildcards.springboot.domain.model.SystemSetting;
import org.wildcards.springboot.domain.repository.ChapterRepository;
import org.wildcards.springboot.domain.repository.OfficerRepository;
import org.wildcards.springboot.domain.repository.SettingsRepository;
import org.wildcards.springboot.infrastructure.rest.AbstractRestRequestHandler;
import org.wildcards.springboot.infrastructure.security.model.Role;
import org.wildcards.springboot.infrastructure.security.model.UserProfile;
import org.wildcards.springboot.infrastructure.security.repository.RoleRepository;
import org.wildcards.springboot.infrastructure.security.repository.UserProfileRepository;


/**
 * 
 * @author jojo
 *
 */
@RestController
@RequestMapping("/api/officer")
public class OfficerController extends AbstractRestRequestHandler {
	
	/**
	 * 
	 */
	private static final Logger LOGGER = Logger.getLogger(OfficerController.class);
	
	/**
	 * 
	 */
	@Autowired
	private OfficerRepository officerRepository;
	
	/**
	 * 
	 */
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	/**
	 * 
	 */
	@Autowired
	private ChapterRepository chapterRepository;
	
	/**
	 * 
	 */
	@Autowired
	private SettingsRepository settingsRepository;
	
	
	@RequestMapping(
			value = "/getPageCountBySize/{size}",
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public Object getPageCountBySize(
    		@PathVariable(value="size") int size) {
		
		long count = officerRepository.getCount();
		long mod = count % size;
		long page = (count / size) + (mod == 0 ? 0 : 1);
		
		return "{\"pages\" : \"" + page + "\"}";
    }
	
	
	@RequestMapping(
			value = "/page/{page}/{size}",
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getPage(
    		@PathVariable(value="page") int page,
    		@PathVariable(value="size") int size) {
		Page<Officer> officers = officerRepository.findAll(new PageRequest(page-1, size));
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("totalElements", officers.getTotalElements());
		result.put("totalPages", officers.getTotalPages());
		result.put("pageIndex", page);
		result.put("pageContent", convertOfficerList(officers.getContent()));
		
		return result;
    }
	
	@RequestMapping(
			value = "/page/{page}/{size}/{searchString}",
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getPage(
    		@PathVariable(value="page") int page,
    		@PathVariable(value="size") int size,
    		@PathVariable(value="searchString") String searchString) {
		Map<String, Object> result = new HashMap<String, Object>();
		Page<Officer> matchingOfficers = officerRepository.searchFor(searchString, new PageRequest(page-1, size));
		
		result.put("totalElements", matchingOfficers.getTotalElements());
		result.put("totalPages", matchingOfficers.getTotalPages());
		result.put("pageIndex", page);
		result.put("pageContent", convertOfficerList(matchingOfficers.getContent()));
		
		return result;
    }
	
	
	private List<OfficerDTO> convertOfficerList(List<Officer> content) {
		List<OfficerDTO> officers = new ArrayList<OfficerDTO>();
		for (Officer officer : content) {
			OfficerDTO dto = convertOfficer(officer);
			officers.add(dto);
		}
		return officers;
	}


	private OfficerDTO convertOfficer(Officer officer) {
		OfficerDTO dto = new OfficerDTO();
		dto.setOfficerId(officer.getId());
		dto.setLastname(officer.getLastname());
		dto.setFirstname(officer.getFirstname());
		dto.setMiddlename(officer.getMiddlename());
		dto.setChapter(officer.getChapter().getChapterName());
		dto.setChapterId(officer.getChapter().getId());
		dto.setRole(officer.getUserProfile().getRole().getRoleName());
		dto.setRoleId(officer.getUserProfile().getRole().getRoleId());
		dto.setUserProfileId(officer.getUserProfile().getUserId());
		dto.setUsername(officer.getUserProfile().getUsername());
		return dto;
	}


	/**
	 * 
	 * @return
	 */
	@RequestMapping(
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<Officer> getAllOfficers(
    		@RequestHeader(value="uid") Long uid,
    		@RequestHeader(value="cid") Long cid) {
		return officerRepository.findAll();
    }

	/**
	 * 
	 * @return
	 */
	@RequestMapping(
			value="/chapter/{chapterid}",
			method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<Officer> getOfficersByChapter(
    		@RequestHeader(value="uid") Long uid,
    		@RequestHeader(value="cid") Long cid,
    		@PathVariable Long chapterId) {
		return null;
		//return officerRepository.getOfficersByChapter(chapterId);
    }
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(
			method=RequestMethod.GET,
			value="/{id}")
	@ResponseStatus(HttpStatus.OK)
    public Officer getOfficer(@PathVariable Long id) {
		return null;
		//return officerRepository.getOne(id);
    }
	
	
	/**
	 * 
	 * @param id
	 */
	@RequestMapping(
			method=RequestMethod.DELETE,
			value="/{id}")
	@ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
		
    }
	
	
	/**
	 * 
	 * @param officer
	 * @return
	 */
	@RequestMapping(
			method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
    public OfficerDTO create(
//    		@RequestHeader(value="uid") Long uid,
//    		@RequestHeader(value="cid") Long cid,
    		@RequestBody OfficerDTO officerDTO) {
		LOGGER.info("officerDTO=" + officerDTO);
		
		Long userId = 1L;
		
		Officer officer = convert(officerDTO);
		officer.setCreatedBy(userId);
		officer.setDateCreated(new Date());
		
		officerRepository.save(officer);
		
		
		return officerDTO;
    }

	private Officer convert(OfficerDTO officerDTO) {
		Officer officer = new Officer();
		Role role = roleRepository.findOne(officerDTO.getRoleId());
		Chapter chapter = chapterRepository.getOne(officerDTO.getChapterId());
		SystemSetting setting = settingsRepository.getByName("default.user.password");
		
		UserProfile userProfile = new UserProfile();
		userProfile.setUsername(officerDTO.getUsername());
		userProfile.setPassword(setting.getValue());
		userProfile.setRole(role);
		
		officer.setChapter(chapter);
		officer.setUserProfile(userProfile);
		officer.setUserProfile(userProfile);
		officer.setFirstname(officerDTO.getFirstname());
		officer.setLastname(officerDTO.getLastname());
		officer.setMiddlename(officerDTO.getMiddlename());
		return officer;
	}


	/**
	 * 
	 * @param officer
	 * @return
	 */
	@RequestMapping(
			method=RequestMethod.PUT,
			value="/{id}")
	@ResponseStatus(HttpStatus.OK)
    public OfficerDTO update(
//    		@RequestHeader(value="uid") Long uid,
//    		@RequestHeader(value="cid") Long cid,
    		@PathVariable Long id, 
    		@RequestBody OfficerDTO officerDTO) {

		Officer officer = officerRepository.getOne(id);
		officer.setFirstname(officerDTO.getFirstname());
		officer.setLastname(officerDTO.getLastname());
		officer.setMiddlename(officerDTO.getMiddlename());
		officer.setChapter(chapterRepository.getOne(officerDTO.getChapterId()));
		officer.getUserProfile().setRole(roleRepository.findOne(officerDTO.getRoleId()));
		officer.getUserProfile().setUsername(officerDTO.getUsername());
		
		officerRepository.saveAndFlush(officer);
		
		return convertOfficer(officer);
    }
}
