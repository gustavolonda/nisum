package com.iverno.gus.userservice.registration.phone.application.adapter;

import java.util.List;
import java.util.stream.Collectors;

import com.iverno.gus.commonservice.endpoint.application.business.object.PhoneBO;
import com.iverno.gus.userservice.registration.phone.domain.entity.PhoneEntity;

public class PhoneAdapter {

	public static PhoneBO phoneEntityToPhoneBO(PhoneEntity phoneEntity) {
		return new PhoneBO().builder()
							.id(phoneEntity.getId())
							.number(phoneEntity.getNumber())
							.cityCode(phoneEntity.getCityCode())
							.contryCode(phoneEntity.getContryCode())
							.build();
		
	}
	
	public static List<PhoneBO> phoneEntityListToPhoneBOList(List<PhoneEntity> phoneEntityList) {
		return phoneEntityList.stream().map(p -> phoneEntityToPhoneBO(p)).collect(Collectors.toList());
		
	}
	
	public static PhoneEntity phoneBOToPhoneEntity(PhoneBO phone) {
		return new PhoneEntity().builder()
							.id(phone.getId())
							.number(phone.getNumber())
							.cityCode(phone.getCityCode())
							.contryCode(phone.getContryCode())
							.build();
		
	}

	public static List<PhoneEntity> phoneBOListToPhoneEntityList(List<PhoneBO> phones) {
		// TODO Auto-generated method stub
		return phones.stream().map(p -> phoneBOToPhoneEntity(p)).collect(Collectors.toList());
	}
	
	

}
