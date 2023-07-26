package com.ajith.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ajith.model.UserDtls;
import com.ajith.repository.UserRepository;
import com.ajith.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	@Override
	public UserDtls createUser(UserDtls user) {

		user.setPassword(passwordEncode.encode(user.getPassword()));
		if(user.getRole() == null) {
			user.setRole("ROLE_USER");
		}
		
		
		return userRepo.save(user);
	}

	@Override
	public boolean checkEmail(String email) {

		return userRepo.existsByEmail(email);
	}
	


	@Override
	public List<UserDtls> getALLuser() {
		return userRepo.findAll();
		
	}

	@Override
	public boolean deleteUserId(int userId) {
			userRepo.deleteById(userId);
		return true;
	}
	
	public UserDtls getUserbyId(int userId) {
		Optional<UserDtls> userFinded = userRepo.findById(userId);
		if(userFinded.isPresent()) {
			return userFinded.get();
		}
		else {
			return null;
		}
		
	}
	
	public void editUserById(UserDtls user) {
	    UserDtls existingUser = getUserbyId(user.getId());

	    // Update the properties of the existing user based on the provided user object
	    if (user.getPassword() != null && !user.getPassword().isEmpty()) {
	        existingUser.setPassword(passwordEncode.encode(user.getPassword()));
	    }
	    
	    if (user.getRole() != null && !user.getRole().isEmpty()) {
	        existingUser.setRole(user.getRole());
	    }

	    // Save the updated existing user
	    userRepo.save(existingUser);
	}

	
	public List<UserDtls> getUserbyName(String  searchterm) {
		return userRepo.findByName(searchterm);
	}
	
	public String getUserNameById(int userId) {
	    UserDtls user = getUserbyId(userId);
	    if (user != null) {
	        return user.getFullName();
	    }
	    return "Unknown"; // Return a default name if the user is not found
	}

}