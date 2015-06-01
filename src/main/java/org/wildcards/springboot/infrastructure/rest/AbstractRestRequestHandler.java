/**
 * 
 */
package org.wildcards.springboot.infrastructure.rest;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.wildcards.springboot.domain.service.validation.ValidationException;

/**
 * 
 * @author jojo
 *
 */
public abstract class AbstractRestRequestHandler {
	
	/**
	 * 
	 */
	private static final String APPLICATION_JSON = "application/json";
	
	/**
	 * 
	 */
	private static final String CONTENT_TYPE = "Content-Type";
	
	/**
	 * 
	 */
	protected Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 
	 * @param ex
	 * @param request
	 * @param response
	 * @return
	 */
    @ExceptionHandler(Exception.class)
    public @ResponseBody RestResponse handleUncaughtException(
    		Exception ex,
    		WebRequest request, 
    		HttpServletResponse response) {
    	logger.info("Converting Uncaught exception to RestResponse : " + ex.getClass().getName());
    	logger.info("Converting Uncaught exception to RestResponse : " + ex.getMessage());
    	
        setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return RestResponse.create(RestResponseType.FAILED, ex.getMessage());
    }
    
    /**
     * 
     * @param ex
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler({EntityNotFoundException.class})
    public @ResponseBody RestResponse handleResourceException(
    		Exception ex,
    		WebRequest request, 
    		HttpServletResponse response) {
    	logger.info("Converting Uncaught exception to RestResponse : " + ex.getMessage());
    	
        setResponse(response, HttpServletResponse.SC_NOT_FOUND);
        return RestResponse.create(RestResponseType.FAILED, ex.getMessage());
    }

    /**
     * 
     * @param response
     */
	private void setResponse(HttpServletResponse response, int responseStatus) {
		response.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        response.setStatus(responseStatus);
	}
	
    /**
     * 
     * @param ex
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public @ResponseBody RestResponse handleIllegalArgumentException(
    		IllegalArgumentException ex, 
    		WebRequest request,
    		HttpServletResponse response) {
    	logger.info("Converting IllegalArgumentException to RestResponse : " + ex.getMessage());
    	
        setResponse(response, HttpServletResponse.SC_BAD_REQUEST);
        return RestResponse.create(RestResponseType.FAILED, ex.getMessage());
    }

    /**
     * 
     * @param ex
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler({ValidationException.class})
    public @ResponseBody RestResponse handleValidationException(
    		Exception ex, 
    		WebRequest request,
    		HttpServletResponse response) {
    	logger.info("Converting ValidationException to RestResponse : " + ex.getMessage());
    	
        setResponse(response, HttpServletResponse.SC_BAD_REQUEST);
        return RestResponse.create(RestResponseType.FAILED, ex.getMessage());
    }
    
    /**
     * 
     * @param ex
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler({DataIntegrityViolationException.class})
    public @ResponseBody RestResponse handleDataIntegrityViolationException(
    		Exception ex, 
    		WebRequest request,
    		HttpServletResponse response) {
    	logger.info("Converting ValidationException to RestResponse : " + ex.getMessage());
    	
        setResponse(response, HttpServletResponse.SC_BAD_REQUEST);
        return RestResponse.create(RestResponseType.FAILED, "Unable to update database due to data integrity violation.");
    }
    
}
