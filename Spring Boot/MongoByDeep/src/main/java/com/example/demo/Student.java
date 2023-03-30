package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="STU")
public class Student {
	@Id
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
