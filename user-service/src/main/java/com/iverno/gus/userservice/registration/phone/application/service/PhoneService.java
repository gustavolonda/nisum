package com.iverno.gus.userservice.registration.phone.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import static  com.iverno.gus.userservice.registration.user.confing.Constraints.MODULE_PHONE;
import com.iverno.gus.commonservice.endpoint.application.service.EndPointServiceImpl;
import com.iverno.gus.commonservice.endpoint.domain.model.StatusDomain;

import static com.iverno.gus.commonservice.endpoint.util.DateUtil.now;
import com.iverno.gus.userservice.registration.phone.domain.entity.PhoneEntity;
import com.iverno.gus.userservice.registration.phone.domain.repository.PhoneRepository;

@Service
@Qualifier("phoneService")
public class PhoneService extends EndPointServiceImpl<PhoneEntity, String> {
	@Autowired
	PhoneRepository repository;
	@Override
	public JpaRepository<PhoneEntity, String> getDao() {
		// TODO Auto-generated method stub
		return this.repository;
	}

	@Override
	public PhoneEntity statusChangeDelete(PhoneEntity entity) {
		entity.setStatus(StatusDomain.DELETE);
		return entity;
	}

	@Override
	public PhoneEntity runCreate(PhoneEntity entity) {
		entity.setCreateDate(now());
		entity.setCreateUpdate(now());
		entity.setStatus(StatusDomain.ACTIVE);
		return entity;
	}

	@Override
	public PhoneEntity runUpdate(PhoneEntity entity) {
		entity.setCreateUpdate(now());
		return entity;
	}

	@Override
	public String nameModule() {
		// TODO Auto-generated method stub
		return MODULE_PHONE ;
	}

}
