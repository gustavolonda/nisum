package com.iverno.gus.securityservice.application.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iverno.gus.commonservice.endpoint.application.business.object.UserBO;
import com.iverno.gus.commonservice.endpoint.confing.FeignResponseDecoderConfig;
import com.iverno.gus.commonservice.endpoint.domain.model.ResponseBase;

@FeignClient(name="user-service",configuration=FeignResponseDecoderConfig.class)
public interface UserClient {
	@RequestMapping(method = RequestMethod.POST, value = "/users")
	 ResponseBase save(@RequestBody UserBO userBO);
	@RequestMapping(method = RequestMethod.GET, value = "/users/{email}")
	ResponseBase searchByEmail(@PathVariable("email") String email);
}
