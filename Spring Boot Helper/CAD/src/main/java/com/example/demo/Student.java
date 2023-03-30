package com.example.demo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="STU")
public class Student {
    
	@Id
	@GeneratedValue
	public int id;
	public String name;
	public String addr;
	int getId() {
		return this.id;
	}
	String getName() {
		return this.name;
	}
	String getAddr() {
		return this.addr;
	}
	void setId(int id) {
		this.id=id;
	}
	void setName(String  name) {
		this.name=name;
	}
	void setAddr(String addr) {
		this.addr=addr;
	}

}
