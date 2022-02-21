package com.iverno.gus.securityservice.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iverno.gus.securityservice.application.model.AuthRequest;
import com.iverno.gus.securityservice.application.service.UserService;

@RestController
@RequestMapping("auth")
public class AuthController {

	private final UserService userService;

	@Autowired
	public AuthController(final UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody AuthRequest authRequest) {
		return ResponseEntity.ok(userService.registerUser(authRequest));
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
		return ResponseEntity.ok(userService.login(authRequest));
	}
}
