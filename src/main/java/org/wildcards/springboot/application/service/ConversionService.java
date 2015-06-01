package org.wildcards.springboot.application.service;

public interface ConversionService<T,S> {

	S convertTo(Class<S> target, T source);
	
	T convertFrom(S source, Class<T> target);
}
