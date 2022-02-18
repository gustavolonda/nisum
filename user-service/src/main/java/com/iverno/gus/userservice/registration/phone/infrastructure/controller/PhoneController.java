package com.iverno.gus.userservice.registration.phone.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iverno.gus.commonservice.endpoint.application.service.IEndPointService;
import com.iverno.gus.commonservice.endpoint.infrastructure.controller.BaseController;
import com.iverno.gus.userservice.registration.phone.domain.entity.PhoneEntity;
@RestController
@RequestMapping("phones")
public class PhoneController extends BaseController{
	@Autowired
	public PhoneController(@Qualifier("phoneService") IEndPointService iEndPointService) {
		this.iEndPointService = iEndPointService;
		
	}
	
	
	@GetMapping
	public ResponseEntity<?> getUserList() {
		return ResponseEntity.ok().body(this.iEndPointService.getAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable long id) {
		return ResponseEntity.ok()
				.body(this.iEndPointService.get(id));

	}
	

	@PostMapping
	public ResponseEntity<?> save(@RequestBody PhoneEntity phoneEntity) {
		return ResponseEntity.ok().body(this.iEndPointService.save(phoneEntity));

	}

	@PutMapping
	public ResponseEntity<?> update( @RequestBody PhoneEntity phoneEntity) {
		return ResponseEntity.ok().body(this.iEndPointService.save(phoneEntity));

	}
	
	 

	 
}
