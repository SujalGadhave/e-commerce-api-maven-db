package org.sujal.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sujal.common.Dbutils;
import org.sujal.dto.User;
import org.sujal.dto.UserRequest;
import org.sujal.dto.UserResponse;

@Component
public class UserService {
	
	@Autowired
	UserResponse response;
	
	@Autowired
	User users;

	//	Save user 
	 public UserResponse addUser(UserRequest request) {
	        
	        try {
	            Dbutils.executeQuery("INSERT INTO users (userId, userName, userEmail, userMobile) "
	                    + "VALUES ('" + request.getUserId() + "', '" + request.getUserName() + "', '"
	                    + request.getUserEmail() + "', '" + request.getUserMobile() + "');");
	            
	            users.setUserId(request.getUserId());
	            users.setUserName(request.getUserName());
	            users.setUserEmail(request.getUserEmail());
	            users.setUserMobile(request.getUserMobile());
	            
	            List<User> userList = new ArrayList<>();
	            userList.add(users);
	            response.setUser(userList);
	            response.setResponseCode("0000");
	            response.setResponseMessage("User added Successfully....");
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            response.setResponseCode("911");
	            response.setResponseMessage("User added failed....");
	        }

	        return response;
	    }
	 
//		Search user by name
	    public UserResponse getUserByFirstName(UserRequest request) throws SQLException {

	        List<User> usersList = new ArrayList<>();

	        ResultSet rs = Dbutils.executeSelectQuery("SELECT * FROM users WHERE userName = '" + request.getUserName() + "';");
	        
	        try {
	        	while (rs.next()) {
	        		
	        		users.setUserId(rs.getString(1));
	        		users.setUserName(rs.getString(2));
	        		users.setUserEmail(rs.getString(3));
	        		users.setUserMobile(rs.getString(4));
	        		usersList.add(users);
	        		
	        		response.setUser(usersList);
	        		response.setResponseCode("0000");
	        		response.setResponseMessage("User found successfully..!");
	        	}
	        }
	        catch (Exception ex) {
	    		
    			response.setResponseCode("911");
    			response.setResponseMessage("User fetched failed...");
    		
    			ex.printStackTrace();
    		
    	}

	        return response;
	    }
	
//	    Display user
	    public UserResponse displayUser() throws SQLException{ 
	
	    	List<User> displayUser = new ArrayList<>();
	    	
	    	try {
	    	ResultSet rs = Dbutils.executeSelectQuery("SELECT * FROM users");
		
	    		while (rs.next()) {
	    			
	    			User users = new User();
	    			
	    			users.setUserId(rs.getString(1));
	    			users.setUserName(rs.getString(2));
	    			users.setUserEmail(rs.getString(3));
	    			users.setUserMobile(rs.getString(4));

	    			displayUser.add(users);
	    		}
	    		response.setUser(displayUser);
	    		response.setResponseCode("0000");
	    		response.setResponseMessage("User fetched");
	    	}
	    	catch (Exception ex) {
	    		
	    			response.setResponseCode("911");
	    			response.setResponseMessage("User fetched failed...");
	    		
	    			ex.printStackTrace();
	    		
	    	}
        
        return response; 
	}
}