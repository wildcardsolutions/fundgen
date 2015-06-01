package org.wildcards.springboot.infrastructure.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

/**
 * 
 * @author jojo
 *
 */
public class ResourceAccessFilter extends GenericFilterBean {

	@Override
	public void doFilter(
			ServletRequest request, 
			ServletResponse response, 
			FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		if (!"GET".equals(httpRequest.getMethod())) {
			System.out.println("getRequestURI=" + httpRequest.getRequestURI());
			System.out.println("getRequestURI=" + httpRequest.getRequestURL());
			System.out.println("getMethod=" + httpRequest.getMethod());
		}
		
		filterChain.doFilter(request, response);
	}

}
