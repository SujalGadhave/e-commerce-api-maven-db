package org.sujal.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserResponse {
	
	private String responseCode;
	private String responseMessage;
	
	private List<User> users;
	
	public List<User> getUser() {
		return users;
	}
	public void setUser(List<User> user) {
		this.users = user;
	}
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
}