package org.sujal.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sujal.dto.UserRequest;
import org.sujal.dto.UserResponse;
import org.sujal.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
//	Save user
	@PostMapping(path="/user", produces = {"application/json" , "application/xml"} , consumes = {"application/json" , "application/xml"})
	public UserResponse saveUser(@RequestBody UserRequest user) {
		return userService.addUser(user);
	}
	
//	Search user by name
	@GetMapping(path = "/user/{firstName}", produces = {"application/json", "application/xml"})
	public UserResponse getUserByFirstName(@PathVariable String firstName) throws SQLException {
	    UserRequest request = new UserRequest();
	    request.setUserName(firstName);
	    return userService.getUserByFirstName(request);
	}

//	Display user
	@GetMapping(path = "/user", produces = {"application/json", "application/xml"})
	public UserResponse displayProduct() throws SQLException{
		return userService.displayUser();
	}
}