package org.sujal.dto;

import java.util.List;

import org.springframework.stereotype.Component;
import org.sujal.entity.User;

@Component
public class UserResponse {
	private String responseCode;
	private String responseMessage;
	
	private List<User> users;
	
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
}