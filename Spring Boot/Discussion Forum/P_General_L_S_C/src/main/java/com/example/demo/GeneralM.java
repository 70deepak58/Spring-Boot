package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Document("general")
public class GeneralM {
@Id
public int id;
public int c_flag;
public int l_flag;
public String message;
public int message_id;
public int getMessage_id() {
	return message_id;
}
public void setMessage_id(int message_id) {
	this.message_id = message_id;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getC_flag() {
	return c_flag;
}
public void setC_flag(int c_flag) {
	this.c_flag = c_flag;
}
public int getL_flag() {
	return l_flag;
}
public void setL_flag(int l_flag) {
	this.l_flag = l_flag;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
}
