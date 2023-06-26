package com.jpa.s.dto;

public class Dto {
	

	
	
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
