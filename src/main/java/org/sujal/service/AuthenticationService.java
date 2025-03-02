package org.sujal.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sujal.dto.LoginResponse;
import org.sujal.entity.Login;
import org.sujal.repository.UserRepo;

@Component
public class AuthenticationService {


	@Autowired
	private UserRepo userRepo;
	
	public LoginResponse login(String loginName, String password) {
		
		LoginResponse response = new LoginResponse();
		
		if(loginName==null || password==null) {
			response.setResponseCode("0911");
			response.setResponseMessage("LoginName and password both are mandatory!");
			
			return response;
		}
		
		try {
			
			Optional<Login> loginDB = userRepo.findByLoginNameAndPassword(loginName, password);
			
			if(loginDB.isPresent()) {
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