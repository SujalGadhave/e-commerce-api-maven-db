package org.sujal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.sujal.dao.UsersDao;
import org.sujal.dto.AddUserRequest;
import org.sujal.dto.UpdateUserRequest;
import org.sujal.dto.UserResponse;
import org.sujal.entity.User;


@Component
public class UserService {
	
	@Autowired
	private UserResponse response;
	
	@Autowired
	private UsersDao usersDao;
	
	//	Save user 
	public UserResponse addUser(AddUserRequest request) {  
		
		List<User> users = new ArrayList<>();
		
		User userTable = new User();
		
		userTable.setId(request.getUserId());
		userTable.setUserName(request.getUserName());
		userTable.setUserEmail(request.getUserEmail());
		userTable.setUserMobile(request.getUserMobile());
		userTable.setLoginName(request.getLoginName());
		userTable.setPassword(request.getPassword());
		 
		userTable = usersDao.save(userTable);
		
		users.add(userTable);
		
		if(userTable != null) {
		
			response.setResponseCode("0000");
			response.setResponseMessage("User added successfully!");
			response.setUsers(users);
		 
			return response;
		
		} else {
			response.setResponseCode("0911");
			response.setResponseMessage("Something went wrong");
			
			return response;
		}
		 
	 }
	 
	public UserResponse updateUser(UpdateUserRequest request) {  
	    List<User> users = usersDao.findByLoginName(request.getLoginName());
	    
	    if (users.isEmpty()) {
	        response.setResponseCode("0911");
	        response.setResponseMessage("User not found!");
	        return response;
	    }
	    
	    User userTable = users.get(0);
	    userTable.setUserName(request.getUserName());
	    userTable.setUserEmail(request.getUserEmail());
	    userTable.setUserMobile(request.getUserMobile());
	    userTable.setPassword(request.getPassword());

	    userTable = usersDao.save(userTable);
	    
	    response.setResponseCode("0000");
	    response.setResponseMessage("User details updated");
	    response.setUsers(List.of(userTable));

	    return response;
	}

	 
//		Search user by name

	public UserResponse getUserByFirstName(String firstName) {  
		List<User> users = usersDao.findByLoginName(firstName);
		
		if (!users.isEmpty()) {
			User user = users.get(0);

			response.setResponseCode("0000");
			response.setResponseMessage("User found!");

			// Set user details in response body
			UpdateUserRequest userRequest = new UpdateUserRequest();
			userRequest.setUserName(user.getUserName());
			userRequest.setUserEmail(user.getUserEmail());
			userRequest.setUserMobile(user.getUserMobile());
			userRequest.setLoginName(user.getLoginName());
			userRequest.setPassword(user.getPassword());
		    
			response.setUsers(users);
			return response;
		  
		} else {
			response.setResponseCode("0911");
			response.setResponseMessage("User not found!");
			return response;
		}
	}
	
	
	public UserResponse displayUser() {
		
		response.setResponseCode("0000");
		response.setResponseMessage("User fetched!");
		response.setUsers(usersDao.findAll());
		return response;
	}
	
	@Transactional // Spring boot creates a container to related db operations and treated like one operation (handling exception) 
	public UserResponse deleteResponse(String loginName) {
	    int deletedCount = usersDao.deleteByLoginName(loginName);
	    
	    if (deletedCount > 0) {
	        response.setResponseCode("0000");
	        response.setResponseMessage("User deleted successfully!");
	    } else {
	        response.setResponseCode("0911");
	        response.setResponseMessage("User not found!");
	    }
	    
	    return response;
	}

}