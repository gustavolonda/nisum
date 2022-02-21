package com.iverno.gus.securityservice.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.iverno.gus.commonservice.endpoint.application.business.object.PhoneBO;
import com.iverno.gus.commonservice.endpoint.application.business.object.UserBO;
import com.iverno.gus.commonservice.endpoint.domain.model.ResponseBase;
import com.iverno.gus.commonservice.endpoint.domain.model.RoleDomain;
import com.iverno.gus.commonservice.endpoint.domain.model.StatusResponseDomain;
import com.iverno.gus.securityservice.application.adapter.AuthRequestAdapter;
import com.iverno.gus.securityservice.application.model.AuthRequest;
import com.iverno.gus.securityservice.application.model.AuthResponse;
import com.iverno.gus.securityservice.application.model.JwtUtil;


@Service
public class UserService {
	
    private final UserClient userClient;
    private final PhoneClient phoneClient;
    private final JwtUtil jwt;

@Autowired
public UserService(UserClient userClient,
					PhoneClient phoneClient,
                   final JwtUtil jwt) {
    this.userClient = userClient;
    this.phoneClient = phoneClient;
    this.jwt = jwt;
}


public AuthResponse register(AuthRequest authRequest, String role) {
    //do validation if user already exists
    authRequest.setPassword(BCrypt.hashpw(authRequest.getPassword(), BCrypt.gensalt()));
  
    ResponseBase<?> responsePhoneSave =  null;
    Gson gson = new Gson();
    if (authRequest.getPhones() != null && authRequest.getPhones().size() > 0) {
    	responsePhoneSave =  phoneClient.saveAll(authRequest.getPhones()); 
	
	}
    
    if (responsePhoneSave == null || responsePhoneSave.getStatus().equalsIgnoreCase(StatusResponseDomain.OK)) {
    	if (responsePhoneSave != null) {
    		List<PhoneBO> phoeList = (List<PhoneBO>) responsePhoneSave.getResult();
    		authRequest.setPhones(phoeList);
		}
    	else {
    		authRequest.setPhones(new ArrayList<>());
		}
    	UserBO userBO = AuthRequestAdapter.authRequestToUserBO(authRequest, role);
    	ResponseBase<?> responseUserSave = userClient.save(userBO);
    	UserBO userResult = (UserBO) responseUserSave.getResult();
    	String accessToken = jwt.generate(userResult, "ACCESS");
        String refreshToken = jwt.generate(userResult, "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
	}
	return null;
    
    
  
    

}


public AuthResponse login(AuthRequest authRequest) {
    //do validation if user already exists
    
    ResponseBase<?> responseUserSave = userClient.searchByEmail(authRequest.getEmail());
    UserBO userResult = (UserBO) responseUserSave.getResult();
    String accessToken = "";
    String refreshToken = "";
    if (userResult != null) {
    	  if (userResult.getPassword().equalsIgnoreCase(authRequest.getPassword())) {
    		    accessToken = jwt.generate(userResult, "ACCESS");
    		    refreshToken = jwt.generate(userResult, "REFRESH");
    		}
		
	}


   return new AuthResponse(accessToken, refreshToken);

    
    
  
    

}

public AuthResponse registerUser(AuthRequest authRequest) {
	return register(authRequest, RoleDomain.USER);
}
public AuthResponse registerAdmin(AuthRequest authRequest) {
	return register(authRequest, RoleDomain.ADMIN);
}
}
