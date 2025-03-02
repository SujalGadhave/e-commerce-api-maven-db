package org.sujal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.sujal.dao.UsersDao;
import org.sujal.dto.AddUserRequest;
import org.sujal.dto.UpdateUserRequest;
import org.sujal.dto.UpdateUserResponse;
import org.sujal.dto.UserResponse;
import org.sujal.entity.User;


@Component
public class UserService {
	
	@Autowired
	private UserResponse response;
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private User userTable;
	
	//	Save user 
	public UserResponse addUser(AddUserRequest request) {  
		
		userTable.setId(request.getUserId());
		userTable.setUserName(request.getUserName());
		userTable.setUserEmail(request.getUserEmail());
		userTable.setPassword(request.getPassword());
		 
		userTable = usersDao.save(userTable);
		
		if(userTable != null) {
		
			response.setResponseCode("0000");
			response.setResponseMessage("User added successfully!");
		 
			return response;
		
		} else {
			response.setResponseCode("0911");
			response.setResponseMessage("Something went wrong");
			
			return response;
		}
		 
	 }
	 
	public UpdateUserResponse updateUser(String userName,UpdateUserRequest request) {  
	 
		User users = usersDao.findByUserName(userName);
	    
		UpdateUserResponse response = new UpdateUserResponse();
		
		if(users == null) {
		   response.setResponseCode("0911");
           response.setResponseMessage("Product not found!");
	       return response;
		}
		
		users.setUserName(request.getUserName());
		users.setUserEmail(request.getUserEmail());
		users.setPassword(request.getPassword());
		
		users = usersDao.save(users);
		
		response.setUserId(users.getId());
		response.setUserName(users.getUserName());
		response.setUserEmail(users.getUserEmail());
		response.setPassword(users.getPassword());
		
		
		response.setResponseCode("0000");
		response.setResponseMessage("User added successfully..!");
	    
		return response;
	}

	 
	//	Search user by name
	public UserResponse getUserByFirstName(String firstName) {  
		User users = usersDao.findByUserName(firstName);
		
		if (users != null) {
			
			response.setResponseCode("0000");
			response.setResponseMessage("User found!");
			
			response.setUserId(users.getId());
			response.setUserName(users.getUserName());
			response.setUserEmail(users.getUserEmail());
			response.setPassword(users.getPassword());
			
			return response;
		  
		} else {
			response.setResponseCode("0911");
			response.setResponseMessage("User not found!");
			return response;
		}
	}
	
	// Display user
	public UserResponse displayUser() {
		
		response.setResponseCode("0000");
		response.setResponseMessage("User fetched!");
		return response;
	}
	
	// Delete user
	@Transactional // Spring boot creates a container to related db operations and treated like one operation (handling exception) 
	public UserResponse deleteResponse(String userName) {
	    
		int deletedCount = usersDao.deleteByUserName(userName);
	    
	    if (deletedCount > 0) {
	        response.setResponseCode("0000");
	        response.setResponseMessage("User deleted successfully!");
	    } else {
	        response.setResponseCode("0911");
	        response.setResponseMessage("Something went wrong!");
	    }
	    
	    return response;
	}
}