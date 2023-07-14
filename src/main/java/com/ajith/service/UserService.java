package com.ajith.service;

import com.ajith.model.UserDtls;

public interface UserService {

	public UserDtls createUser(UserDtls user);

	public boolean checkEmail(String email);
	
	

}