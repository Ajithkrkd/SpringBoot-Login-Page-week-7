package com.ajith.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class UserDtls {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String fullName;

	private String email;

	private String address;

	private String qualification;

	private String password;
	
	private String role;
	
	
	
	
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getFullName() {
		return fullName;
	}




	public void setFullName(String fullName) {
		this.fullName = fullName;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public String getQualification() {
		return qualification;
	}




	public void setQualification(String qualification) {
		this.qualification = qualification;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getRole() {
		return role;
	}




	public void setRole(String role) {
		this.role = role;
	}
	 public boolean checkvalid()
	    {
	    	if(email.isEmpty() || address.isEmpty() || password.isEmpty() || qualification.isEmpty() || fullName.isEmpty())
	    	{
	    		return false;
	    	}
	    	return true;
	    }



	@Override
	public String toString() {
		return "UserDtls [id=" + id + ", fullName=" + fullName + ", email=" + email + ", address=" + address
				+ ", qualification=" + qualification + ", password=" + password + ", role=" + role + "]";
	}

}


