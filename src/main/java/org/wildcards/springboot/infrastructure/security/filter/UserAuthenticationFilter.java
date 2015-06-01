package org.wildcards.springboot.infrastructure.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

/**
 * 
 * @author jojo
 *
 */
public class UserAuthenticationFilter extends GenericFilterBean  {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
			ServletException {
		 final HttpServletRequest httpRequest = (HttpServletRequest) request;
	      final HttpServletResponse httpResponse = (HttpServletResponse) response;
	      System.out.println("request is " + httpRequest.getClass());
	      System.out.println("request URL : " + httpRequest.getRequestURL());
	      System.out.println("response is " + httpResponse.getClass());
	      filterChain.doFilter(request, response);
		
	}

}
