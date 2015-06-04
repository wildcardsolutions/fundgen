package org.wildcards.springboot.application.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.wildcards.springboot.domain.model.DeclarationPeriod;
import org.wildcards.springboot.domain.model.SystemSetting;
import org.wildcards.springboot.domain.repository.SettingsRepository;
import org.wildcards.springboot.infrastructure.rest.AbstractRestRequestHandler;

/**
 * 
 * @author jojo
 *
 */
@RestController
@RequestMapping("/api/declarationPeriods")
public class DeclarationPeriodController extends AbstractRestRequestHandler {

	/**
	 * 
	 */
	private static final SimpleDateFormat SDF = new SimpleDateFormat("MMMM dd");
	
	/**
	 * 
	 */
	private static final String FIRST_HALF_CUTOFF_DATE = "declarationPeriod.firsthalf.cutOffDay";
	
	/**
	 * 
	 */
	private static final String SECOND_HALF_CUTOFF_DATE = "declarationPeriod.secondhalf.cutOffDay";
	
	public static final int DECLARATION_CURRENT = 1;
	public static final int DECLARATION_ADVANCE = 2;
	public static final int DECLARATION_SUPPLEMENTAL = 3;
	
	/**
	 * 
	 */
	@Autowired
	private SettingsRepository settingsRepository;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<DeclarationPeriod> getDeclarationPeriods() {
		List<DeclarationPeriod> declarationPeriods = new ArrayList<DeclarationPeriod>();
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		declarationPeriods.add(getCurrentPeriod(year, month));
		declarationPeriods.add(getAdvancePeriod(year, month));
		declarationPeriods.add(getSupplementalPeriod());
		return declarationPeriods;
	}

	/**
	 * 
	 * @return
	 */
	private DeclarationPeriod getAdvancePeriod(int year, int month) {
		DeclarationPeriod period = new DeclarationPeriod();
		period.setId(DECLARATION_ADVANCE);
		period.setCode("Advance");
		if (isFirstHalfOfTheMonth()) {
			period.setDateStart(createDate(year, month, 16));
			period.setDateEnd(createDate(year, month, getLastDate(year, month)));
			period.setLabel("Advance: " + SDF.format(period.getDateStart()) + " - " + SDF.format(period.getDateEnd()));
			if (month==Calendar.DECEMBER) {
				period.setCutoffDate(createDate(year+1, Calendar.JANUARY, 10));
			} else {
				period.setCutoffDate(createDate(year, month+1, 10));
			}
		} else {
			if (month==Calendar.JANUARY) {
				year = year - 1;
				month = 12;
			}  else {
//				month = (month);
			}
			period.setDateStart(createDate(year, month, 1));
			period.setDateEnd(createDate(year, month, 15));
			period.setCutoffDate(createDate(year, month, 25));
			period.setLabel("Advance :" + SDF.format(period.getDateStart()) + " - " + SDF.format(period.getDateEnd()));
		}
		return period;
	}

	/**
	 * 
	 * @return
	 */
	private DeclarationPeriod getCurrentPeriod(int year, int month) {
		DeclarationPeriod period = new DeclarationPeriod();
		period.setId(DECLARATION_CURRENT);
		period.setCode("Current");
		if (isFirstHalfOfTheMonth()) {
			period.setDateStart(createDate(year, month, 1));
			period.setDateEnd(createDate(year, month, 15));
			period.setCutoffDate(createDate(year, month, 25));
			period.setLabel("Current :" + SDF.format(period.getDateStart()) + " - " + SDF.format(period.getDateEnd()));
		} else {
			period.setDateStart(createDate(year, month-1, 16));
			period.setDateEnd(createDate(year, month-1, getLastDate(year, month-1)));
			period.setLabel("Current :" + SDF.format(period.getDateStart()) + " - " + SDF.format(period.getDateEnd()));
			if (month==Calendar.JANUARY) {
				period.setCutoffDate(createDate(year-1, Calendar.DECEMBER, 10));
			} else {
				period.setCutoffDate(createDate(year, month+1, 10));
			}
			
		}
		
		return period;
	}

	/**
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	private int getLastDate(int year, int month) {
		if (isLeapYear(year) && month == Calendar.FEBRUARY) {
			return 29;
		}
		switch (month) {
			case Calendar.JANUARY:
			case Calendar.MARCH:
			case Calendar.MAY:
			case Calendar.JULY:
			case Calendar.AUGUST:
			case Calendar.OCTOBER:
			case Calendar.DECEMBER:
				return 31;
			case Calendar.FEBRUARY:
				return 28;
			default:
				return 30;
		}
	}

	/**
	 * 
	 * @param year
	 * @return
	 */
	private boolean isLeapYear(int year) {
		Calendar cal = Calendar.getInstance();
		boolean isLeapYear = true;
		try {
			cal.set(year, 2, 29);
		} catch (Exception e) {
			isLeapYear = false;
		}
		return isLeapYear;
	}

	/**
	 * 
	 * @return
	 */
	private DeclarationPeriod getSupplementalPeriod() {
		DeclarationPeriod period = new DeclarationPeriod();
		period.setId(DECLARATION_SUPPLEMENTAL);
		period.setCode("Supplemental");
		period.setLabel("Supplemental");
		period.setDateStart(createDate(1970, 0, 1));
		period.setDateEnd(createDate(2099, 11, 31));
		return period;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean isFirstHalfOfTheMonth() {
		Calendar calendar = Calendar.getInstance();
		SystemSetting firstHalfCutOff = settingsRepository.getByName(FIRST_HALF_CUTOFF_DATE);
		SystemSetting secondHalfCutOff = settingsRepository.getByName(SECOND_HALF_CUTOFF_DATE);
		
		return calendar.get(Calendar.DAY_OF_MONTH) <= Integer.parseInt(firstHalfCutOff.getValue()) &&
				calendar.get(Calendar.DAY_OF_MONTH) >  Integer.parseInt(secondHalfCutOff.getValue()) ? true : false;
	}
	
	/**
	 * 
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	private Date createDate(int year, int month, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, date);
		return new Date(calendar.getTimeInMillis());
	}
}
