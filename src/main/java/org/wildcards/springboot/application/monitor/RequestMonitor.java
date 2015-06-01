package org.wildcards.springboot.application.monitor;


import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class RequestMonitor {
	
	/**
	 * 
	 */
	private static final Logger LOGGER = Logger.getLogger(RequestMonitor.class);
	
	
	@Before("execution(* org.wildcards.springboot..*.*(..))")
	public void logServiceAccessEntry(JoinPoint joinPoint) {
		String packageName = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		LOGGER.info(packageName + "." + methodName + "() args.length=" +  args.length);
		LOGGER.info(packageName + "." + methodName + "() args=" + Arrays.toString(args));
	}
	
	@AfterReturning("execution(* org.wildcards.springboot..*.*(..))")
	public void logServiceAccess(JoinPoint joinPoint) {
		System.out.println("Completed: " + joinPoint);
	}
	
	private void showArgs(Object[] args) {
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
	}
}