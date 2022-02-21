package com.iverno.gus.securityservice.application.service;

import static com.iverno.gus.commonservice.endpoint.confing.Constants.UNEXPECTED_ERROR;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

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
import com.iverno.gus.commonservice.endpoint.application.exception.BaseException;
import com.iverno.gus.commonservice.endpoint.domain.model.ResponseBase;
import com.iverno.gus.commonservice.endpoint.domain.model.RoleDomain;
import com.iverno.gus.commonservice.endpoint.domain.model.StatusResponseDomain;
import com.iverno.gus.commonservice.endpoint.util.ConvertUtil;
import com.iverno.gus.securityservice.application.adapter.AuthRequestAdapter;
import com.iverno.gus.securityservice.application.model.AuthRequest;
import com.iverno.gus.securityservice.application.model.AuthResponse;
import com.iverno.gus.securityservice.application.model.JwtUtil;
import static com.iverno.gus.securityservice.config.Constraints.MODULE_SECURITY;

import lombok.SneakyThrows;


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

@SneakyThrows
public AuthResponse register(AuthRequest authRequest, String role) {
	try {
		authRequest.setPassword(BCrypt.hashpw(authRequest.getPassword(), BCrypt.gensalt()));
		  
	    ResponseBase<?> responsePhoneSave =  null;
	   
	    UserBO userBO = AuthRequestAdapter.authRequestToUserBO(authRequest, role);
	    userBO.setPhones(new ArrayList<>());
		ResponseBase<?> responseUserSave = null;
		UserBO userResult = null;
	    responseUserSave = userClient.save(userBO);
	    	 
	    if (responseUserSave != null && responseUserSave.getStatus().equalsIgnoreCase(StatusResponseDomain.OK)) {
	    	userResult = (UserBO) ConvertUtil.linkedHashMapToObject((LinkedHashMap<String, Object>) responseUserSave.getResult(),  UserBO.class);
	    	List<PhoneBO> phoneBOs = new ArrayList<>();
	    	for (PhoneBO phoneBO : authRequest.getPhones()) {
	    		phoneBO.setUserId(userResult.getId());
	    		phoneBOs.add(phoneBO);
			}
	    		
	    	if (phoneBOs.size() > 0) {
	    		responsePhoneSave =  phoneClient.saveAll(phoneBOs);
	    		if (responsePhoneSave.getStatus().equalsIgnoreCase(StatusResponseDomain.OK)) {
	    			List<?> linkedHashMapUserBOList = (List) responsePhoneSave.getResult();
	    			List<PhoneBO> phoneList = linkedHashMapUserBOList.stream().map(p -> {
	    				return (PhoneBO) ConvertUtil.linkedHashMapToObject((LinkedHashMap<String, Object>) p,  PhoneBO.class);
	    									
	    			}).collect(Collectors.toList());
	    			userBO.setPhones(phoneList);
					
				}
			}
	    	 
	    	
	    	
	    	String accessToken = jwt.generate(userResult, "ACCESS");
	        String refreshToken = jwt.generate(userResult, "REFRESH");

	        return new AuthResponse(accessToken, refreshToken);
		}
	    Exception exception = new Exception();
	     throw new BaseException().builder()
									.status(StatusResponseDomain.ERROR)
									.message("No de pudo guardar")
									.module(MODULE_SECURITY)
									.exception(exception)
									.build();
	} catch (Exception e) {
		throw new BaseException().builder()
								.status(StatusResponseDomain.ERROR)
								.message(UNEXPECTED_ERROR)
								.module(MODULE_SECURITY)
								.exception(e)
								.build();
	}
    
    
    
    
  
    

}

@SneakyThrows
public AuthResponse login(AuthRequest authRequest) {
    //do validation if user already exists
	try {
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

		} catch (Exception e) {
			throw new BaseException().builder()
									.status(StatusResponseDomain.ERROR)
									.message(UNEXPECTED_ERROR)
									.module(MODULE_SECURITY)
									.exception(e)
									.build();
}
    
  
    

}

public AuthResponse registerUser(AuthRequest authRequest) {
	return register(authRequest, RoleDomain.USER);
}
public AuthResponse registerAdmin(AuthRequest authRequest) {
	return register(authRequest, RoleDomain.ADMIN);
}
}
