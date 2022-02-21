package com.iverno.gus.securityservice.application.adapter;


import java.util.ArrayList;

import com.iverno.gus.commonservice.endpoint.application.business.object.UserBO;
import com.iverno.gus.securityservice.application.model.AuthRequest;
public class AuthRequestAdapter {
	
	public static UserBO authRequestToUserBO(AuthRequest authRequest, String role) {
		return new UserBO().builder()
							.name(authRequest.getName())
							.email(authRequest.getEmail())
							.password(authRequest.getPassword())
							.role(role)
							.phones(authRequest.getPhones() != null && authRequest.getPhones().size() >= 0 ? authRequest.getPhones(): new ArrayList<>())
							.build();
		
	}
	
	

}

