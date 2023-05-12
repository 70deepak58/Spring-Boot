package com.user.dto;

public class Dto {
	
//	private String username;
//	private String pwd;
//	public Dto(String username, String pwd) {
//		super();
//		this.username = username;
//		this.pwd = pwd;
//	}
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getPwd() {
//		return pwd;
//	}
//	public void setPwd(String pwd) {
//		this.pwd = pwd;
//	}
//	public Dto() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	@Override
//	public String toString() {
//		return "Dto [username=" + username + ", pwd=" + pwd + "]";
//	}
	
	
	private String id;
	private String pwd;
	public Dto(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "Dto [id=" + id + ", pwd=" + pwd + "]";
	}
	public Dto() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
