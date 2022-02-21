package com.iverno.gus.securityservice.application.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iverno.gus.commonservice.endpoint.application.business.object.PhoneBO;
import com.iverno.gus.commonservice.endpoint.confing.FeignResponseDecoderConfig;
import com.iverno.gus.commonservice.endpoint.domain.model.ResponseBase;

@FeignClient(name="user-service",configuration=FeignResponseDecoderConfig.class)
public interface PhoneClient {
	@RequestMapping(method = RequestMethod.POST, value = "/phones/phoneListSave")
	 ResponseBase saveAll(@RequestBody List<PhoneBO> phoneBOs);
}
