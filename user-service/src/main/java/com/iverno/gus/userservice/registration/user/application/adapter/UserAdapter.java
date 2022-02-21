package com.iverno.gus.userservice.registration.user.application.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.iverno.gus.commonservice.endpoint.application.business.object.UserBO;
import com.iverno.gus.userservice.registration.phone.application.adapter.PhoneAdapter;
import com.iverno.gus.userservice.registration.user.domain.entity.UserEntity;

public class UserAdapter {
	
	public static UserBO userEntityToUserBO(UserEntity userEntity) {
		return new UserBO().builder()
							.id(userEntity.getId())
							.name(userEntity.getName())
							.password(userEntity.getPassword())
							.email(userEntity.getEmail())
							.role(userEntity.getRole())
							.phones(userEntity.getPhones() != null && userEntity.getPhones().size() >= 0 ? PhoneAdapter.phoneEntityListToPhoneBOList(userEntity.getPhones()): new ArrayList<>())
							.build();
	}
	
	public static List<UserBO> userEntityListToUserBOList(List<UserEntity> userEntityList) {
		return userEntityList.stream().map(u -> userEntityToUserBO(u)).collect(Collectors.toList());
	}
	
	public static UserEntity userBOToUserEntity(UserBO userBO) {
		return new UserEntity().builder()
								.id(userBO.getId())
								.name(userBO.getName())
								.password(userBO.getPassword())
								.email(userBO.getEmail())
								.role(userBO.getRole())
								.phones(userBO.getPhones() != null && userBO.getPhones().size() >= 0 ? PhoneAdapter.phoneBOListToPhoneEntityList(userBO.getPhones()): new ArrayList<>())
								.build();
	}
	
	public static List<UserEntity> userBOListToUserEntityList(List<UserBO> userBOList) {
		return userBOList.stream().map(u -> userBOToUserEntity(u)).collect(Collectors.toList());
	}
	
	
	
}
