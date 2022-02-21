package com.iverno.gus.commonservice.endpoint.application.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.loader.plan.exec.process.internal.AbstractRowReader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.iverno.gus.commonservice.endpoint.application.exception.BaseException;
import static com.iverno.gus.commonservice.endpoint.confing.Constants.UNEXPECTED_ERROR;
import com.iverno.gus.commonservice.endpoint.domain.model.StatusResponseDomain;

import lombok.SneakyThrows;

import java.util.Optional;
@Service
public abstract class  EndPointServiceImpl<T, ID> implements IEndPointService<T, ID>{
	
	@Override
	@SneakyThrows
	public T save(T entity) {
		try {
			entity = runCreate(entity);
	       	return getDao().save(entity);
		} catch (Exception e) {
			throw new BaseException().builder()
									.status(StatusResponseDomain.ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
		
	}
	
	@Override
	@SneakyThrows
	public List<T> saveAll(List<T> entityList) {
		try {
			entityList.forEach( entity -> {
				entity = this.runCreate(entity);
			});
			List<T>  entityListResult = this.getDao().saveAll(entityList);
			
			return entityListResult;
		}catch (Exception e) {
			throw new BaseException().builder()
									.status(StatusResponseDomain.ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
	}

	@Override
	@SneakyThrows
	public T update(T entity) {
		try {
			entity = runUpdate(entity);
			return getDao().save(entity);
		}catch (Exception e) {
			throw new BaseException().builder()
									.status(StatusResponseDomain.ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
	}

	@Override
	@SneakyThrows
	public T delete(ID id) {
		try {	T entity = get(id);
			return statusChangeDelete(entity);
		}catch (Exception e) {
				throw new BaseException().builder()
										.status(StatusResponseDomain.ERROR)
										.message(UNEXPECTED_ERROR)
										.module(nameModule())
										.exception(e)
										.build();
		}
	}

	@Override
	@SneakyThrows
	public T get(ID id) {
		try {	
			Optional<T> obj = getDao().findById(id);
			if (obj.isPresent()) {
				return obj.get();
			}
			return null;
		}catch (Exception e) {
			throw new BaseException().builder()
									.status(StatusResponseDomain.ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
	}
	}

	@Override
	@SneakyThrows
	public List<T> getAll() {
		try {	
			List<T> returnList = new ArrayList<>();
			getDao().findAll().forEach(obj -> returnList.add(obj));
			return returnList;
		}catch (Exception e) {
			throw new BaseException().builder()
									.status(StatusResponseDomain.ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
	}
	
	

	public abstract JpaRepository<T, ID> getDao();
	public abstract T statusChangeDelete(T entity);
	public abstract T runCreate(T entity);
	public abstract T runUpdate(T entity);
	public abstract String nameModule();
}
