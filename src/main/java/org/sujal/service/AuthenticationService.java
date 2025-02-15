package org.sujal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sujal.dto.LoginResponse;
import org.sujal.entity.User;
import org.sujal.repository.UserRepo;

@Component
public class AuthenticationService {

	@Autowired
	private LoginResponse response;
	
	@Autowired
	private UserRepo userRepo;
	
	public LoginResponse login(String loginName, String password) {
		
		if(loginName==null || password==null) {
			response.setResponseCode("0911");
			response.setResponseMessage("LoginName and password both are mandatory!");
			
			return response;
		}
		
		try {
			
			User userDB = userRepo.findByLoginNameAndPassword(loginName, password);
			
			if(null != userDB) {
				response.setResponseCode("0000");
				response.setResponseMessage("Login successful!");
				return response;
			}
			else {
				response.setResponseCode("0911");
				response.setResponseMessage("Login Failed!");
				return response;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setResponseCode("0911");
			response.setResponseMessage("Internal server error occured!");
			return response;
		}
	}
}