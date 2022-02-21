package com.iverno.gus.userservice.registration.user.application.service;

import static com.iverno.gus.commonservice.endpoint.confing.Constants.UNEXPECTED_ERROR;
import static com.iverno.gus.commonservice.endpoint.util.DateUtil.now;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import static  com.iverno.gus.userservice.registration.user.confing.Constraints.MODULE_USER;
import com.iverno.gus.commonservice.endpoint.application.exception.BaseException;
import com.iverno.gus.commonservice.endpoint.application.service.EndPointServiceImpl;
import com.iverno.gus.commonservice.endpoint.domain.model.StatusDomain;
import com.iverno.gus.commonservice.endpoint.domain.model.StatusResponseDomain;
import com.iverno.gus.userservice.registration.user.domain.entity.UserEntity;
import com.iverno.gus.userservice.registration.user.domain.repository.UserRepository;

import lombok.SneakyThrows;

@Service
@Qualifier("userService")

public class UserService  extends EndPointServiceImpl<UserEntity, String> {
	@Autowired
	UserRepository repository;
	
	@Override
	public JpaRepository<UserEntity, String> getDao() {
		// TODO Auto-generated method stub
		return this.repository;
	}

	@Override
	public UserEntity statusChangeDelete(UserEntity entity) {
		entity.setStatus(StatusDomain.DELETE);
		return this.update(entity);
	}

	@Override
	public UserEntity runCreate(UserEntity entity) {
		entity.setCreateDate(now());
		entity.setLastLogin(now());
		entity.setCreateUpdate(now());
		entity.setStatus(StatusDomain.ACTIVE);
		return entity;
	}

	@Override
	public UserEntity runUpdate(UserEntity entity) {
		entity.setCreateUpdate(now());
		return entity;
	}
	@SneakyThrows
	public UserEntity searchByEmail(String email) {
		
		try {	
			return this.repository.findFirstByEmail(email);
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
	public String nameModule() {
		// TODO Auto-generated method stub
		return MODULE_USER;
	}
	

	


}
