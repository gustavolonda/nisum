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

import com.iverno.gus.commonservice.endpoint.application.business.object.PhoneBO;
import com.iverno.gus.commonservice.endpoint.application.service.IEndPointService;
import com.iverno.gus.commonservice.endpoint.domain.model.ResponseBase;
import com.iverno.gus.commonservice.endpoint.domain.model.StatusResponseDomain;
import com.iverno.gus.commonservice.endpoint.infrastructure.controller.BaseController;
import com.iverno.gus.userservice.registration.phone.application.adapter.PhoneAdapter;
import com.iverno.gus.userservice.registration.phone.domain.entity.PhoneEntity;
import static  com.iverno.gus.userservice.registration.user.confing.Constraints.MODULE_PHONE;

import static com.iverno.gus.commonservice.endpoint.confing.Constants.FINISHED_SUCCESSFULLY;

import java.util.List;
@RestController
@RequestMapping("phones")
public class PhoneController extends BaseController{
	private  String className = this.getClass().getSimpleName(); 
	@Autowired
	public PhoneController(@Qualifier("phoneService") IEndPointService iEndPointService) {
		this.iEndPointService = iEndPointService;
		
	}
	
	
	@GetMapping
	public ResponseEntity<?> getPhoneList() {
		return ResponseEntity.ok().body(new ResponseBase<>().builder()
															.message(FINISHED_SUCCESSFULLY)
															.result(PhoneAdapter.phoneEntityListToPhoneBOList(iEndPointService.getAll()))
															.className(className)
															.module(MODULE_PHONE)
															.build());

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPhone(@PathVariable long id) {
		return ResponseEntity.ok()
				.body(new ResponseBase<>().builder()
										.message(FINISHED_SUCCESSFULLY)
										.result(PhoneAdapter.phoneEntityToPhoneBO((PhoneEntity) this.iEndPointService.get(id)))
										.className(className)
										.module(MODULE_PHONE)
										.build());

	}
	

	@PostMapping
	public ResponseEntity<?> save(@RequestBody PhoneBO phoneBO) {
		return ResponseEntity.ok().body(new ResponseBase<>().builder()
														.status(StatusResponseDomain.OK)
														.message(FINISHED_SUCCESSFULLY)
														.result(PhoneAdapter.phoneEntityToPhoneBO((PhoneEntity) this.iEndPointService.save(PhoneAdapter.phoneBOToPhoneEntity(phoneBO))))
														.className(className)
														.module(MODULE_PHONE)
														.build());

	}
	
	@PostMapping("/phoneListSave")
	public ResponseEntity<?> saveAll(@RequestBody List<PhoneBO> phoneBOList) {
		return ResponseEntity.ok().body(new ResponseBase<>().builder()
														.status(StatusResponseDomain.OK)
														.message(FINISHED_SUCCESSFULLY)
														.result(PhoneAdapter.phoneEntityListToPhoneBOList( this.iEndPointService.saveAll(PhoneAdapter.phoneBOListToPhoneEntityList(phoneBOList))))
														.className(className)
														.module(MODULE_PHONE)
														.build());

	}
	
	@PutMapping
	public ResponseEntity<?> update( @RequestBody  PhoneBO phoneBO) {
		return ResponseEntity.ok().body(new ResponseBase<>().builder()
														.status(StatusResponseDomain.OK)
														.message(FINISHED_SUCCESSFULLY)
														.result(PhoneAdapter.phoneEntityToPhoneBO((PhoneEntity) this.iEndPointService.save(PhoneAdapter.phoneBOToPhoneEntity(phoneBO))))
														.className(className)
														.module(MODULE_PHONE)
														.build());

	}
	
	 

	 
}
