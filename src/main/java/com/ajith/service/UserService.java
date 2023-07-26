package com.ajith.service;

import java.util.List;

import com.ajith.model.UserDtls;

public interface UserService {

	 UserDtls createUser(UserDtls user);

	public boolean checkEmail(String email);
	
	List<UserDtls> getALLuser();
	
	public boolean deleteUserId(int userId) ;

	
	
		
	

}