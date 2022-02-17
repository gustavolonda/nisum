package com.iverno.gus.commonservice.endpoint.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public abstract class  EndPointServiceImpl<T, ID> implements IEndPointService<T, ID>{

	@Override
	public T save(T entity) {
       	return getDao().save(entity);
	}


	@Override
	public T delete(ID id) {
		T entity = get(id);
		return setEntityStatus(entity);
	}

	@Override
	public T get(ID id) {
		Optional<T> obj = getDao().findById(id);
		if (obj.isPresent()) {
			return obj.get();
		}
		return null;
	}

	@Override
	public List<T> getAll() {
		List<T> returnList = new ArrayList<>();
		getDao().findAll().forEach(obj -> returnList.add(obj));
		return returnList;
	}

	public abstract JpaRepository<T, ID> getDao();
	public abstract T setEntityStatus(T entity);
	public abstract T setDateCreate(T entity);
	public abstract T setDateUpdate(T entity);
}
