package com.iverno.gus.userservice.registration.user.infrastructure.controller;

import static com.iverno.gus.commonservice.endpoint.confing.Constants.FINISHED_SUCCESSFULLY;
import static com.iverno.gus.userservice.registration.user.confing.Constraints.MODULE_USER;

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

import com.iverno.gus.commonservice.endpoint.application.business.object.UserBO;
import com.iverno.gus.commonservice.endpoint.application.service.IEndPointService;
import com.iverno.gus.commonservice.endpoint.domain.model.ResponseBase;
import com.iverno.gus.commonservice.endpoint.domain.model.StatusResponseDomain;
import com.iverno.gus.commonservice.endpoint.infrastructure.controller.BaseController;
import com.iverno.gus.userservice.registration.user.application.adapter.UserAdapter;
import com.iverno.gus.userservice.registration.user.application.service.UserService;
import com.iverno.gus.userservice.registration.user.domain.entity.UserEntity;
@RestController
@RequestMapping("users")
public class UserController extends BaseController{
	private  String className = this.getClass().getSimpleName(); 
	@Autowired
	public UserController(@Qualifier("userService") IEndPointService iEndPointService) {
		this.iEndPointService = iEndPointService;
		
	}
	
	@GetMapping
	public ResponseEntity<?> getUserList() {
		return ResponseEntity.ok().body(new ResponseBase<>().builder()
															.status(StatusResponseDomain.OK)
															.message(FINISHED_SUCCESSFULLY)
															.result(UserAdapter.userEntityListToUserBOList(this.iEndPointService.getAll()))
															.className(className)
															.module(MODULE_USER)
															.build());

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable long id) {
		return ResponseEntity.ok()
				.body(new ResponseBase<>().builder()
											.status(StatusResponseDomain.OK)
											.message(FINISHED_SUCCESSFULLY)
											.result(UserAdapter.userEntityToUserBO((UserEntity) this.iEndPointService.get(id)))
											.className(className)
											.module(MODULE_USER)
											.build());

	}
	
	@GetMapping("/searchByEmail/{email}")
	public ResponseEntity<?> getUser(@PathVariable String email) {
		UserService userService = (UserService) this.iEndPointService;
		return ResponseEntity.ok()
				.body(new ResponseBase<>().builder()
											.status(StatusResponseDomain.OK)
											.message(FINISHED_SUCCESSFULLY)
											.result(UserAdapter.userEntityToUserBO((UserEntity) userService.searchByEmail(email)))
											.className(className)
											.module(MODULE_USER)
											.build());

	}
	

	@PostMapping
	public ResponseEntity<?> save(@RequestBody UserBO userBO) {
		return ResponseEntity.ok().body(new ResponseBase<>().builder()
										.status(StatusResponseDomain.OK)
										.message(FINISHED_SUCCESSFULLY)
										.result(UserAdapter.userEntityToUserBO((UserEntity) this.iEndPointService.save(UserAdapter.userBOToUserEntity(userBO))))
										.className(className)
										.module(MODULE_USER)
										.build());

	}

	@PutMapping
	public ResponseEntity<?> update( @RequestBody UserBO userBO) {
		return ResponseEntity.ok().body(new ResponseBase<>().builder()
										.status(StatusResponseDomain.OK)
										.message(FINISHED_SUCCESSFULLY)
										.result(UserAdapter.userEntityToUserBO((UserEntity) this.iEndPointService.save(UserAdapter.userBOToUserEntity(userBO))))
										.className(className)
										.module(MODULE_USER)
										.build());
	}

}
