package com.iverno.gus.commonservice.endpoint.application.service;

import java.util.List;

public interface IEndPointService <T, ID> {

	T save(T entity);

	T delete(ID id);
	
	T get(ID id);
	
	List<T> getAll();
}