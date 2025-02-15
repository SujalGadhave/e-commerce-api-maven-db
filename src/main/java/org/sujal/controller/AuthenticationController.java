package org.sujal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.sujal.dto.LoginRequest;
import org.sujal.dto.LoginResponse;
import org.sujal.service.AuthenticationService;

@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authService;
	
	@PostMapping(path="/auth/login",  produces = {"application/json" , "application/xml"} , consumes = {"application/json" , "application/xml"})
	public LoginResponse login(@RequestBody LoginRequest request) {
		return authService.login(request.getLoginName(), request.getPassword());
	}
}
