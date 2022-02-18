package com.iverno.gus.commonservice.endpoint.infrastructure.controller;

import org.springframework.stereotype.Component;

import com.iverno.gus.commonservice.endpoint.application.service.IEndPointService;


@Component
public class BaseController {
	protected IEndPointService iEndPointService;
}
