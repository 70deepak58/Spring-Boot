package com.product.entity;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//@Document(collection = "user")
public class User {

	@Id
	private  String id;
	private  String username;
	private  String email;
	private  String password;
	private  String mobileNumber;
	private  boolean power=false;
	public User() {
		super();
	}
	
	
	public User(String id, String username, String email, String password, String mobileNumber, boolean power) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.power = power;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public boolean isPower() {
		return power;
	}
	public void setPower(boolean power) {
		this.power = power;
	}

	//List<String> list=new ArrayList<String>();  
	//private  HashMap<Integer,List<String> > comment=new HashMap<Integer,List<String>>();
	//String comment;

	
}
