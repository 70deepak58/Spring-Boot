package com.jpa.s.entity;

import org.springframework.data.annotation.Id;
//import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="UserDetail")
public class UserInfo {
	
	@Id
	private String id;
	private String name;
	private String email;
	private String mobileNumber;
	//extra added by deepak
	private String password;
	private String roles="ROLE_USER";
	public UserInfo(String id, String name, String email,String mobileNumber , String roles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobileNumber=mobileNumber;
		
		this.roles = roles;    //ROLE_USER, ROLE_ADMIN
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", email=" + email + ", mobileNumber=" + mobileNumber
				+  ", roles=" + roles + "]";
	}
	
	
	

}
