package org.sujal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.sujal.dto.AddUserRequest;
import org.sujal.dto.UpdateUserRequest;
import org.sujal.dto.UserResponse;
import org.sujal.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	//	Save user
	@PostMapping(path="/user", produces = {"application/json" , "application/xml"} , consumes = {"application/json" , "application/xml"})
	public UserResponse saveUser(@RequestBody AddUserRequest request) {
		return userService.addUser(request);
	}
	
	//	Update user
	@PutMapping(path="/user", produces = {"application/json" , "application/xml"} , consumes = {"application/json" , "application/xml"})
	public UserResponse updateUser(@RequestBody UpdateUserRequest request) {
		return userService.updateUser(request);
	}
	
	@DeleteMapping(path="/user/{loginName}", produces = {"application/json" , "application/xml"})
	public UserResponse deleteResponse (@PathVariable String loginName) {
		return userService.deleteResponse(loginName);
	}
	
	//	Search user by name
	@GetMapping(path = "/user/{firstName}", produces = {"application/json", "application/xml"})
	public UserResponse getUserByFirstName(@PathVariable String firstName) {
	    return userService.getUserByFirstName(firstName);
	}
	
	// Display user 
	@GetMapping(path = "/user", produces = {"application/json", "application/xml"})
	public UserResponse displayUser() {
	    return userService.displayUser();
	}
}